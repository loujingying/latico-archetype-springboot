package com.latico.archetype.springboot.common.lock;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @author: latico
 * @date: 2020-01-10 14:46
 * @version: 1.0
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

    /**
     * 时间戳字符串
     * @return
     */
    protected String getTimestampStr() {
        return new Timestamp(System.currentTimeMillis()).toString();
    }

    @Override
    public String getLockKey() {
        return null;
    }

    @Override
    public String getLockValue() {
        return null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AbstractDistributedLock{");
        sb.append("expireTime=").append(expireTime);
        sb.append(", locked=").append(locked);
        sb.append(", tryInterval=").append(tryInterval);
        sb.append('}');
        return sb.toString();
    }
}
