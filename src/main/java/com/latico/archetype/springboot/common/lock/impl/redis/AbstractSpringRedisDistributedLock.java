package com.latico.archetype.springboot.common.lock.impl.redis;

import com.latico.archetype.springboot.common.lock.AbstractDistributedLock;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.UUID;

/**
 * <PRE>
 * spring的redis抽象锁
 * </PRE>
 *
 * @Author: latico
 * @Date: 2020-01-09 16:41
 * @Version: 1.0
 */
public abstract class AbstractSpringRedisDistributedLock extends AbstractDistributedLock {

    protected StringRedisTemplate redisTemplate;

    /**
     * 调用set后的返回值
     */
    protected static final String OK = "OK";

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
     * 调用者指定了锁的值,固定不变的锁值
     */
    protected boolean fixedLockValue;

    /**
     * 初始化锁
     * @param redisTemplate 操作模板
     * @param lockKey 锁的key，建议最好带有 lock_前缀
     */
    public AbstractSpringRedisDistributedLock(StringRedisTemplate redisTemplate, String lockKey) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey;
    }

    /**
     * 初始化锁
     * @param redisTemplate
     * @param lockKey 锁的key，建议最好带有 lock_前缀
     * @param lockValue lock对应的值
     */
    public AbstractSpringRedisDistributedLock(StringRedisTemplate redisTemplate, String lockKey, String lockValue) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey;
        this.lockValue = lockValue;
        this.fixedLockValue = true;
    }

    /**
     * 初始化锁
     * @param redisTemplate 操作模板
     * @param lockKey 锁的key，建议最好带有 lock_前缀
     * @param expireTime 锁的过期时间
     */
    public AbstractSpringRedisDistributedLock(StringRedisTemplate redisTemplate, String lockKey, int expireTime) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey;
        this.expireTime = expireTime;
    }

    /**
     * 初始化锁
     * @param redisTemplate 操作模板
     * @param lockKey 锁的key，建议最好带有 lock_前缀
     * @param lockValue     锁的值
     * @param expireTime 锁的过期时间
     */
    public AbstractSpringRedisDistributedLock(StringRedisTemplate redisTemplate, String lockKey, String lockValue, int expireTime) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey;
        this.lockValue = lockValue;
        this.fixedLockValue = true;
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
     * 刷新lock的值
     * 建议子类覆盖
     */
    protected void refreshLockValue() {
        //调用者没有指定的情况下，就初始化
        if (!fixedLockValue) {
            this.lockValue = UUID.randomUUID().toString();
        }
    }


    public String getLockKey() {
        return lockKey;
    }

    public String getLockValue() {
        return lockValue;
    }

    public void setLockKey(String lockKey) {
        this.lockKey = lockKey;
    }

    public void setLockValue(String lockValue) {
        this.lockValue = lockValue;
    }


    /**
     * lock的信息，用于日志打印
     *
     * @return
     */
    protected String getLockInfo() {
        return "[" + lockKey + "||" + lockValue + "]";
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append(", lockKey='").append(lockKey).append('\'');
        sb.append(", lockValue='").append(lockValue).append('\'');
        sb.append(", fixedLockValue='").append(fixedLockValue).append('\'');
        sb.append(", locked=").append(locked);
        sb.append(", expireTime=").append(expireTime);
        sb.append(", tryInterval=").append(tryInterval);
        sb.append('}');
        return sb.toString();
    }
}
