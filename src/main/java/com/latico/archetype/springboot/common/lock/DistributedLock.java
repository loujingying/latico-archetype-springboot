package com.latico.archetype.springboot.common.lock;

/**
 * <PRE>
 * 分布式锁
 * </PRE>
 *
 * @Author: latico
 * @Date: 2020-01-10 9:26
 * @Version: 1.0
 */
public interface DistributedLock {

    /**
     * 关闭,不关闭，可以重复使用
     */
    void close();

    /**
     * 尝试获取锁，获取失败就不断轮询尝试
     * @param timeout 超时时间，单位毫秒，如果是小于等于0，那么一直获取直到拿到锁
     * @return 是否成功获得锁
     */
    boolean tryLock(long timeout);

    /**
     * 一次性获取锁，直接返回成功或失败，不像tryLock的轮询
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
