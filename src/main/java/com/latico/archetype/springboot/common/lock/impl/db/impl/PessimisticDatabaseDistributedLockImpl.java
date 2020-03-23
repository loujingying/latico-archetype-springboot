package com.latico.archetype.springboot.common.lock.impl.db.impl;

import com.latico.archetype.springboot.common.lock.impl.db.AbstractDatabaseDistributedLock;

/**
 * <PRE>
 * 悲观锁，排它锁，使用 for update方式实现
 * 该方式不需要使用过期时间，因为会一直阻塞到拿到锁为止
 * </PRE>
 *
 * @author: latico
 * @date: 2020-01-10 10:43
 * @version: 1.0
 */
public class PessimisticDatabaseDistributedLockImpl extends AbstractDatabaseDistributedLock {
    public PessimisticDatabaseDistributedLockImpl(String lockKey) {
        super(lockKey);
    }

    public PessimisticDatabaseDistributedLockImpl(String lockKey, int expireTime) {
        super(lockKey, expireTime);
    }

    public PessimisticDatabaseDistributedLockImpl(String lockKey, String lockValue, int expireTime) {
        super(lockKey, lockValue, expireTime);
    }

    @Override
    public boolean tryLock(long timeout) {
        return false;
    }

    @Override
    public boolean lock() {
        return false;
    }

    @Override
    public boolean unlock() {
        return false;
    }
}
