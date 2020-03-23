package com.latico.archetype.springboot.common.lock.impl.redis.impl;

import com.latico.archetype.springboot.common.lock.impl.redis.AbstractSpringRedisDistributedLock;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.commands.JedisCommands;
import redis.clients.jedis.params.SetParams;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @author: latico
 * @date: 2020-01-09 16:38
 * @version: 1.0
 */
public class SpringRedisDistributedLockImpl extends AbstractSpringRedisDistributedLock {

    private static final Logger LOG = LoggerFactory.getLogger(SpringRedisDistributedLockImpl.class);

    public SpringRedisDistributedLockImpl(StringRedisTemplate redisTemplate, String lockKey) {
        super(redisTemplate, lockKey);
    }

    public SpringRedisDistributedLockImpl(StringRedisTemplate redisTemplate, String lockKey, String lockValue) {
        super(redisTemplate, lockKey, lockValue);
    }

    public SpringRedisDistributedLockImpl(StringRedisTemplate redisTemplate, String lockKey, String lockValue, int expireTime) {
        super(redisTemplate, lockKey, lockValue, expireTime);
    }

    public SpringRedisDistributedLockImpl(StringRedisTemplate redisTemplate, String lockKey, int expireTime) {
        super(redisTemplate, lockKey, expireTime);
    }

    @Override
    public void close() {

    }

    @Override
    public boolean tryLock(long timeout) {
        if (isLocked()) {
            LOG.warn("已经上锁:{}", getLockInfo());
            return true;
        }

        //判断是不是无线等待
        boolean infinite = false;
        if (timeout <= 0) {
            infinite = true;
        }

        //超时参数等
        SetParams setParams = new SetParams();
        setParams.nx();
        setParams.ex(expireTime);

        final long startTime = System.currentTimeMillis();

        refreshLockValue();
        try {
            while (true) {
                //判断是否达到超时，不是无限的达到了超时
                if (!infinite && ((System.currentTimeMillis() - startTime) > timeout)) {
                    break;
                }

                if (OK.equalsIgnoreCase(executeSet(lockKey, lockValue, setParams))) {
                    locked = true;
                    break;
                }

                LOG.debug("没有获得锁{}，睡眠等待:{}", getLockInfo(), getTryInterval());
                // 每次请求等待一段时间
                Thread.sleep(getTryInterval());
            }
        } catch (Exception e) {
            LOG.error("", e);
        }
        return locked;
    }


    @Override
    public boolean lock() {
        if (isLocked()) {
            LOG.warn("已经上锁:{}", getLockInfo());
            return true;
        }
        //超时参数等
        SetParams setParams = new SetParams();
        setParams.nx();
        setParams.ex(expireTime);
        refreshLockValue();
        //不存在则添加 且设置过期时间（单位ms）
        String result = executeSet(lockKey, lockValue, setParams);
        locked = OK.equalsIgnoreCase(result);
        return locked;
    }

    /**
     * 解锁
     * <p>
     * 可以通过以下修改，让这个锁实现更健壮：
     * <p>
     * 不使用固定的字符串作为键的值，而是设置一个不可猜测（non-guessable）的长随机字符串，作为口令串（token）。
     * 不使用 DEL 命令来释放锁，而是发送一个 Lua 脚本，这个脚本只在客户端传入的值和键的口令串相匹配时，才对键进行删除。
     * 这两个改动可以防止持有过期锁的客户端误删现有锁的情况出现。
     *
     * @return
     */
    @Override
    public boolean unlock() {
        if (!locked) {
            LOG.warn("没有上锁:{}", getLockInfo());
            return true;
        }
        try {
            Boolean status = redisTemplate.execute((org.springframework.data.redis.connection.RedisConnection connection) -> {
                Object nativeConnection = connection.getNativeConnection();
                Long result = 0L;

                java.util.List<String> keys = new ArrayList<>();
                keys.add(lockKey);
                java.util.List<String> values = new ArrayList<>();
                values.add(lockValue);

                // 集群模式
                if (nativeConnection instanceof JedisCluster) {
                    result = (Long) ((JedisCluster) nativeConnection).eval(UNLOCK_LUA_SCRIPT, keys, values);
                }

                // 单机模式
                if (nativeConnection instanceof Jedis) {
                    result = (Long) ((Jedis) nativeConnection).eval(UNLOCK_LUA_SCRIPT, keys, values);
                }

                //成功状态
                boolean succ = result == 1;
                if (succ) {
                    locked = false;
                    LOG.info("Redis分布式锁，解锁{}成功！解锁时间：{}", getLockInfo(), getTimestampStr());
                } else {
                    LOG.error("Redis分布式锁，解锁{}失败！解锁时间：{}", getLockInfo(), getTimestampStr());
                }

                return succ;
            });

        } catch (Throwable e) {
            LOG.warn("Redis不支持EVAL命令，使用降级方式解锁：{}", e, getLockInfo());
            degradedUnlock();
        }

        return !locked;
    }

    /**
     * 降级方式解锁
     */
    private void degradedUnlock() {
        try {
            String value = executeGet(lockKey);
            if (lockValue.equals(value)) {
                redisTemplate.delete(lockKey);
                locked = false;
            }
        } catch (Exception e) {
            LOG.error("", e);
        }
        if (!locked) {
            LOG.info("Redis分布式锁，解锁{}成功！解锁时间：{}", getLockInfo(), getTimestampStr());
        } else {
            LOG.error("Redis分布式锁，解锁{}失败！解锁时间：{}", getLockInfo(), getTimestampStr());
        }
    }

    /**
     * 重写redisTemplate的set方法
     * <p>
     * 命令 SET resource-name anystring NX EX max-lock-time 是一种在 Redis 中实现锁的简单方法。
     * <p>
     * 客户端执行以上的命令：
     * <p>
     * 如果服务器返回 OK ，那么这个客户端获得锁。
     * 如果服务器返回 NIL ，那么客户端获取锁失败，可以在稍后再重试。
     *
     * @param key       锁的Key
     * @param value     锁里面的值
     * @param setParams
     * @return
     */
    private String executeSet(final String key, final String value, final SetParams setParams) {
        return redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(org.springframework.data.redis.connection.RedisConnection connection) throws org.springframework.dao.DataAccessException {
                Object nativeConnection = connection.getNativeConnection();
                String result = null;
                if (nativeConnection instanceof JedisCommands) {
                    result = ((JedisCommands) nativeConnection).set(key, value, setParams);
                }

                if (OK.equals(result)) {
                    LOG.info("尝试获取锁成功:{},时间:{}", getLockInfo(), getTimestampStr());
                } else {
                    LOG.debug("尝试获取锁失败:{},时间:{}", getLockInfo(), getTimestampStr());
                }

                return result;
            }
        });
    }

    /**
     * 获取redis里面的值
     *
     * @param key key
     * @return T
     */
    private <T> T executeGet(final String key) {
        return redisTemplate.execute((org.springframework.data.redis.connection.RedisConnection connection) -> {
            Object nativeConnection = connection.getNativeConnection();
            Object result = null;
            if (nativeConnection instanceof JedisCommands) {
                result = ((JedisCommands) nativeConnection).get(key);
            }
            return (T) result;
        });
    }
}
