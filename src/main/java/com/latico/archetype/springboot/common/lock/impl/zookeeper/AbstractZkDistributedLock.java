package com.latico.archetype.springboot.common.lock.impl.zookeeper;

import com.latico.archetype.springboot.common.lock.AbstractDistributedLock;

/**
 * <PRE>
 * zookeeper的抽象锁
 * </PRE>
 *
 * @author: latico
 * @date: 2020-01-09 16:41
 * @version: 1.0
 */
public abstract class AbstractZkDistributedLock extends AbstractDistributedLock {
    protected String connectString;
    protected String lockNodePath = "/lock_node";
    @Override
    public String getLockKey() {
        return lockNodePath;
    }

    @Override
    public String getLockValue() {
        return null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AbstractZkDistributedLock{");
        sb.append("connectString='").append(connectString).append('\'');
        sb.append(", lockNodePath='").append(lockNodePath).append('\'');
        sb.append(", expireTime=").append(expireTime);
        sb.append(", locked=").append(locked);
        sb.append(", tryInterval=").append(tryInterval);
        sb.append('}');
        return sb.toString();
    }
}
