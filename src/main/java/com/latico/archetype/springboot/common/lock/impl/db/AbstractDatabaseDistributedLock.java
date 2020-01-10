package com.latico.archetype.springboot.common.lock.impl.db;

import com.latico.archetype.springboot.common.lock.AbstractDistributedLock;

import java.util.UUID;

/**
 * <PRE>
 * database的抽象锁
 * </PRE>
 *
 * @Author: latico
 * @Date: 2020-01-09 16:41
 * @Version: 1.0
 */
public abstract class AbstractDatabaseDistributedLock extends AbstractDistributedLock {
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

    public AbstractDatabaseDistributedLock(String lockKey) {
        this.lockKey = lockKey;
    }

    public AbstractDatabaseDistributedLock(String lockKey, int expireTime) {

        this.lockKey = lockKey;
        this.expireTime = expireTime;
    }
    public AbstractDatabaseDistributedLock(String lockKey, String lockValue, int expireTime) {
        this.lockKey = lockKey;
        this.lockValue = lockValue;
        this.fixedLockValue = true;
        this.expireTime = expireTime;
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

    /**
     * lock的信息，用于日志打印
     *
     * @return
     */
    protected String getLockInfo() {
        return "[" + lockKey + "||" + lockValue + "]";
    }
}
