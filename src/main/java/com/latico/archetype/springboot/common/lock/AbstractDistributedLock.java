package com.latico.archetype.springboot.common.lock;

import java.util.UUID;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @Author: latico
 * @Date: 2020-01-10 14:46
 * @Version: 1.0
 */
public abstract class AbstractDistributedLock implements DistributedLock {
    /**
     * 默认锁的有效时间(s)
     */
    protected static final int DEFAULT_EXPIRE = 60;

    /**
     * 锁的有效时间(s)
     */
    protected int expireTime = DEFAULT_EXPIRE;

    /**
     * 锁标记
     */
    protected boolean locked = false;

    /**
     * 尝试的时候，间隔时间，单位秒
     */
    protected int tryInterval = 2000;



    @Override
    public boolean isLocked() {
        return locked;
    }

    @Override
    public int getExpireTime() {
        return getExpireTime();
    }


    @Override
    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public void close() {

    }

    /**
     * 尝试的间隔
     *
     * @return 单位毫秒
     */
    protected long getTryInterval() {
        return tryInterval;
    }

}