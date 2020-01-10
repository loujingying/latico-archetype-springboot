package com.latico.archetype.springboot.common.lock.impl.zookeeper;

import com.latico.archetype.springboot.common.lock.AbstractDistributedLock;

/**
 * <PRE>
 * zookeeper的抽象锁
 * </PRE>
 *
 * @Author: latico
 * @Date: 2020-01-09 16:41
 * @Version: 1.0
 */
public abstract class AbstractZkDistributedLock extends AbstractDistributedLock {
    protected String connectString;
    protected String lockNodePath = "/lock_node";
}
