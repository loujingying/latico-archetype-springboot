package com.latico.archetype.springboot.common.lock.impl.zookeeper.impl;

import com.latico.archetype.springboot.common.lock.DistributedLock;
import com.latico.archetype.springboot.common.util.ThreadUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class CuratorZkDistributedLockImplTest {

    @Test
    public void tryLock() {

        String connectString = "172.168.10.7:2181";

        String lockNodePath = "/lock_node";
        int sessionTimeoutMs = 60000;
        int connectionTimeoutMs = 15000;
        DistributedLock distributedLock = new CuratorZkDistributedLockImpl(connectString, lockNodePath, sessionTimeoutMs, connectionTimeoutMs);

        System.out.println(distributedLock.tryLock(10000));
        ThreadUtils.sleep(15000);
        distributedLock.unlock();
        ThreadUtils.sleep(5000);
        System.out.println(distributedLock.tryLock(10000));
        ThreadUtils.sleep(10000);
        distributedLock.unlock();
        ThreadUtils.sleep(5000);

        distributedLock.close();
        ThreadUtils.sleep(2000);
    }

    @Test
    public void tryLock2() {

        String connectString = "172.168.10.7:2181";

        String lockNodePath = "/lock_node";
        int sessionTimeoutMs = 60000;
        int connectionTimeoutMs = 15000;
        DistributedLock distributedLock = new CuratorZkDistributedLockImpl(connectString, lockNodePath, sessionTimeoutMs, connectionTimeoutMs);

        System.out.println(distributedLock.tryLock(10000));
        ThreadUtils.sleep(15000);
        distributedLock.unlock();
        ThreadUtils.sleep(5000);
        System.out.println(distributedLock.tryLock(10000));
        ThreadUtils.sleep(10000);
        distributedLock.unlock();
        ThreadUtils.sleep(5000);

        distributedLock.close();
        ThreadUtils.sleep(2000);
    }

    @Test
    public void lock() {

        String connectString = "172.168.10.7:2181";

        String lockNodePath = "/lock_node";
        int sessionTimeoutMs = 60000;
        int connectionTimeoutMs = 15000;
        DistributedLock distributedLock = new CuratorZkDistributedLockImpl(connectString, lockNodePath, sessionTimeoutMs, connectionTimeoutMs);

        System.out.println(distributedLock.lock());

        ThreadUtils.sleep(10000);
        distributedLock.unlock();
        ThreadUtils.sleep(5000);
        distributedLock.close();
        ThreadUtils.sleep(2000);
    }


}