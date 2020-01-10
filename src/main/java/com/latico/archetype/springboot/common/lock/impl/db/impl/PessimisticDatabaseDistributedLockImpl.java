package com.latico.archetype.springboot.common.lock.impl.db.impl;

import com.latico.archetype.springboot.common.lock.impl.db.AbstractDatabaseDistributedLock;

/**
 * <PRE>
 * 悲观锁，排它锁，使用 for update方式实现
 * 该方式不需要使用过期时间，因为会一直阻塞到拿到锁为止
 * </PRE>
 *
 * @Author: latico
 * @Date: 2020-01-10 10:43
 * @Version: 1.0
 */
public class PessimisticDatabaseDistributedLockImpl extends AbstractDatabaseDistributedLock {
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
