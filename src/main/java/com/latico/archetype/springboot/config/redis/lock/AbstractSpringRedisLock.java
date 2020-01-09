package com.latico.archetype.springboot.config.redis.lock;

import java.util.UUID;

import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * <PRE>
 * 抽象锁
 * </PRE>
 *
 * @Author: latico
 * @Date: 2020-01-09 16:41
 * @Version: 1.0
 */
public abstract class AbstractSpringRedisLock implements RedisLock {

    protected org.springframework.data.redis.core.StringRedisTemplate redisTemplate;

    /**
     * 调用set后的返回值
     */
    protected static final String OK = "OK";

    /**
     * 默认锁的有效时间(s)
     */
    protected static final int DEFAULT_EXPIRE = 60;
    /**
     * 锁的有效时间(s)
     */
    protected int expireTime = DEFAULT_EXPIRE;

    /**
     * 解锁的lua脚本
     */
    protected final String UNLOCK_LUA_SCRIPT = getUnLockLua();

    /**
     * 锁标志对应的key
     */
    protected String lockKey;

    /**
     * 锁对应的值
     */
    protected String lockValue;

    /**
     * 锁标记
     */
    protected boolean locked = false;

    /**
     * 尝试的时候，间隔时间，单位秒
     */
    protected long tryInterval = 2000;

    /**
     * 初始化锁
     * @param redisTemplate 操作模板
     * @param lockKey 锁的key，建议最好带有 lock_前缀
     */
    public AbstractSpringRedisLock(org.springframework.data.redis.core.StringRedisTemplate redisTemplate, String lockKey) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey;
        initLockValue();
    }

    /**
     * 初始化锁
     * @param redisTemplate
     * @param lockKey 锁的key，建议最好带有 lock_前缀
     * @param lockValue lock对应的值
     */
    public AbstractSpringRedisLock(org.springframework.data.redis.core.StringRedisTemplate redisTemplate, String lockKey, String lockValue) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey;
        this.lockValue = lockValue;
    }

    /**
     * 初始化锁
     * @param redisTemplate 操作模板
     * @param lockKey 锁的key，建议最好带有 lock_前缀
     * @param expireTime 锁的过期时间
     */
    public AbstractSpringRedisLock(StringRedisTemplate redisTemplate, String lockKey, int expireTime) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey;
        this.expireTime = expireTime;
        initLockValue();
    }

    /**
     * 初始化锁
     * @param redisTemplate 操作模板
     * @param lockKey 锁的key，建议最好带有 lock_前缀
     * @param lockValue     锁的值
     * @param expireTime 锁的过期时间
     */
    public AbstractSpringRedisLock(StringRedisTemplate redisTemplate, String lockKey, String lockValue, int expireTime) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey;
        this.lockValue = lockValue;
        this.expireTime = expireTime;
    }

    /**
     * 解锁的LUA事务脚本
     *
     * @return LUA脚本
     */
    protected String getUnLockLua() {
        StringBuilder sb = new StringBuilder();
        sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        sb.append("then ");
        sb.append("    return redis.call(\"del\",KEYS[1]) ");
        sb.append("else ");
        sb.append("    return 0 ");
        sb.append("end ");
        return sb.toString();
    }

    /**
     * 初始化lock的值
     * 建议子类覆盖
     */
    protected void initLockValue() {
        this.lockValue = UUID.randomUUID().toString();
    }

    @Override
    public boolean isLocked() {
        return locked;
    }

    @Override
    public String getLockKey() {
        return lockKey;
    }

    @Override
    public int getExpireTime() {
        return getExpireTime();
    }

    @Override
    public String getLockValue() {
        return lockValue;
    }

    @Override
    public void setLockKey(String lockKey) {
        this.lockKey = lockKey;
    }

    @Override
    public void setLockValue(String lockValue) {
        this.lockValue = lockValue;
    }

    @Override
    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * lock的信息，用于日志打印
     *
     * @return
     */
    protected String getLockInfo() {
        return "[" + lockKey + "||" + lockValue + "]";
    }

    /**
     * 尝试的间隔
     *
     * @return 单位毫秒
     */
    protected long getTryInterval() {
        return tryInterval;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append(", lockKey='").append(lockKey).append('\'');
        sb.append(", lockValue='").append(lockValue).append('\'');
        sb.append(", locked=").append(locked);
        sb.append(", expireTime=").append(expireTime);
        sb.append(", tryInterval=").append(tryInterval);
        sb.append('}');
        return sb.toString();
    }
}
