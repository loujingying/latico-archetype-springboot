package com.latico.archetype.springboot.common.lock.impl.zookeeper.impl;

import com.latico.archetype.springboot.common.lock.impl.zookeeper.AbstractZkDistributedLock;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

/**
 * <PRE>
 *  建议创建后通过单例重复利用，因为创建zookeeper连接耗时很长。
 * 利用临时节点，可以避免dump机了能自动清除锁节点
 * 锁的过期时间，可以利用sessionTimeoutMs作为等同
 * 该对象可以重复获取和释放锁，CuratorFramework是可以多次获取锁的，但是这里封装的只能获取一次锁，只有释放了才能再获取一次锁，
 * 获取锁的原理是往zookeeper里面写入一个zookeeper自动顺序的临时节点，然后监听自己的节点名称是不是最小值，如果是就是获得了锁，
 * 释放锁就是删除自己的节点，后面的节点会监听到自己前面的节点被删除，然后判断自己是不是最小的节点。
 * </PRE>
 *
 * @Author: latico
 * @Date: 2020-01-10 14:43
 * @Version: 1.0
 */
public class CuratorZkDistributedLockImpl extends AbstractZkDistributedLock {

    private static final Logger LOG = LoggerFactory.getLogger(CuratorZkDistributedLockImpl.class);

    private CuratorFramework client;
    private InterProcessMutexImpl curatorLock;
    /**
     * 每次跟zookeeper交互操作时的会话超时，不是zookeeper的最大连接时间，所以CuratorFramework对象是可以无限使用，只要不close()
     */
    protected int sessionTimeoutMs = 60000;
    protected int connectionTimeoutMs = 15000;
    protected String getLockInfo() {
        if (curatorLock != null) {
            return "[" + lockNodePath + "||" + curatorLock.getLockPath() + "]";
        } else {
            return "[" + lockNodePath + "]";
        }

    }

    /**
     * @param connectString  连接字符串，支持多个，逗号隔开     localhost:2181,localhost:2182,localhost:2183
     * @param lockNodePath      必须/开头  建议是 /lock_node
     * @param sessionTimeoutMs 每次跟zookeeper交互操作时的会话超时，不是zookeeper的最大连接时间，所以CuratorFramework对象是可以无限使用，只要不close()
     * @param connectionTimeoutMs
     */
    public CuratorZkDistributedLockImpl(String connectString, String lockNodePath, int sessionTimeoutMs, int connectionTimeoutMs) {
        this.connectString = connectString;
        this.lockNodePath = lockNodePath;
        this.sessionTimeoutMs = sessionTimeoutMs;
        this.expireTime = sessionTimeoutMs / 1000;
        this.connectionTimeoutMs = connectionTimeoutMs;
        connect();
    }

    private void connect() {
        // 重试策略，初始化每次重试之间需要等待的时间，基准等待时间为5秒。
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(5000, 3);

        // 使用默认的会话时间（60秒）和连接超时时间（15秒）来创建 Zookeeper 客户端
        client = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .connectionTimeoutMs(connectionTimeoutMs)
                .sessionTimeoutMs(sessionTimeoutMs)
                .retryPolicy(retryPolicy)
                .build();

        // 启动客户端
        client.start();

        //创建锁对象
        curatorLock = new InterProcessMutexImpl(client, lockNodePath);
    }

    @Override
    public void close() {
        if (client != null) {
            client.close();
        }
    }

    @Override
    public boolean tryLock(long timeout) {

        if (isLocked()) {
            LOG.warn("已经上锁:{}", getLockInfo());
            return true;
        }

        try {

            if (timeout <= 0) {
                curatorLock.acquire();
                this.locked = true;
            } else {
                boolean acquire = curatorLock.acquire(timeout, TimeUnit.MILLISECONDS);

                if (acquire) {
                    this.locked = true;
                }
            }
        } catch (Exception e) {
            LOG.error("", e);
            this.locked = false;
        }

        printGetLockInfo();
        return locked;
    }

    private void printGetLockInfo() {
        if (locked) {
            LOG.info("获取锁成功:{},时间:{}", getLockInfo(), getTimestampStr());
        } else {
            LOG.warn("获取锁失败:{},时间:{}", getLockInfo(), getTimestampStr());
        }
    }

    @Override
    public boolean lock() {
        if (isLocked()) {
            LOG.warn("已经上锁:{}", getLockInfo());
            return true;
        }

        try {
            boolean acquire = curatorLock.acquire(5, TimeUnit.SECONDS);
            if (acquire) {
                this.locked = true;
            }
        } catch (Exception e) {
            LOG.error("", e);
            this.locked = false;
        }
        printGetLockInfo();
        return locked;
    }

    @Override
    public boolean unlock() {
        if (!locked) {
            LOG.warn("没有上锁:{}", getLockInfo());
            return true;
        }
        if (curatorLock.isAcquiredInThisProcess()) {
            try {
                String lockInfo = getLockInfo();
                LOG.info("开始解锁:{}", lockInfo);
                curatorLock.release();
                locked = false;
                LOG.info("解锁成功:{}", lockInfo);
            } catch (Exception e) {
                LOG.error("解锁异常", e);
            }
        } else {
            LOG.warn("不需要解锁:{}", getLockInfo());
            locked = false;
        }

        return !locked;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CuratorZkDistributedLockImpl{");
        sb.append("client=").append(client);
        sb.append(", curatorLock=").append(curatorLock);
        sb.append(", sessionTimeoutMs=").append(sessionTimeoutMs);
        sb.append(", connectionTimeoutMs=").append(connectionTimeoutMs);
        sb.append(", connectString='").append(connectString).append('\'');
        sb.append(", lockNodePath='").append(lockNodePath).append('\'');
        sb.append(", lockInfo='").append(getLockInfo()).append('\'');
        sb.append(", expireTime=").append(expireTime);
        sb.append(", locked=").append(locked);
        sb.append(", tryInterval=").append(tryInterval);
        sb.append('}');
        return sb.toString();
    }
}
