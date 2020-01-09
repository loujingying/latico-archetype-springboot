package com.latico.archetype.springboot.config.redis.lock;

/**
 * <PRE>
 * redis的分布式锁接口类
 * </PRE>
 *
 * @Author: latico
 * @Date: 2020-01-09 16:38
 * @Version: 1.0
 */
public interface RedisLock {

    /**
     * 尝试获取锁，轮询尝试
     * @param timeout 超时时间，单位毫秒，如果是小于等于0，那么一直获取直到拿到锁
     * @return 是否成功获得锁
     */
    boolean tryLock(long timeout);

    /**
     * 一次性获取锁，不像tryLock的轮询
     * @return 是否成功获得锁
     */
    boolean lock();

    /**
     * 解除锁定
     * @return 是否解锁成功
     */
    boolean unlock();

    /**
     * @return 是否锁定状态
     */
    boolean isLocked();

    /**
     * 获取锁的key
     * @return
     */
    String getLockKey();

    /**
     * @param lockKey
     */
    void setLockKey(String lockKey);

    /**
     * 获取锁对应的值
     * @return
     */
    String getLockValue();

    /**
     * @param lockValue
     */
    void setLockValue(String lockValue);

    /**
     * 过期时间
     * @return 单位秒
     */
    int getExpireTime();

    /**
     * 设置过期时间
     * @param expireTime
     */
    void setExpireTime(int expireTime);

}
