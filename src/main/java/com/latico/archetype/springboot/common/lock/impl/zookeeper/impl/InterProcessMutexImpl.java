package com.latico.archetype.springboot.common.lock.impl.zookeeper.impl;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.LockInternalsDriver;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @Author: latico
 * @Date: 2020-01-10 16:09
 * @Version: 1.0
 */
public class InterProcessMutexImpl extends InterProcessMutex {

    public InterProcessMutexImpl(CuratorFramework client, String path) {
        super(client, path);
    }

    public InterProcessMutexImpl(CuratorFramework client, String path, LockInternalsDriver driver) {
        super(client, path, driver);
    }

    @Override
    public String getLockPath() {
        return super.getLockPath();
    }
}
