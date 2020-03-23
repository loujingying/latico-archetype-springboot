package com.latico.archetype.springboot.common.lock.impl.db.impl;

import com.latico.archetype.springboot.common.lock.impl.db.AbstractDatabaseDistributedLock;
import com.latico.archetype.springboot.common.lock.impl.db.dao.JpaDatabaseLockDao;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import com.latico.commons.spring.util.SpringUtils;

/**
 * <PRE>
 * 通过事务的方式进行查询和更新
 * 该方式需要使用单独线程扫描是否锁过期，进行处理
 * </PRE>
 *
 * @author: latico
 * @date: 2020-01-10 10:43
 * @version: 1.0
 */
public class JpaDatabaseDistributedLockImpl extends AbstractDatabaseDistributedLock {

    private static final Logger LOG = LoggerFactory.getLogger(JpaDatabaseDistributedLockImpl.class);

    public JpaDatabaseDistributedLockImpl(String lockKey) {
        super(lockKey);
    }

    public JpaDatabaseDistributedLockImpl(String lockKey, int expireTime) {
        super(lockKey, expireTime);
    }

    public JpaDatabaseDistributedLockImpl(String lockKey, String lockValue, int expireTime) {
        super(lockKey, lockValue, expireTime);
    }

    @Override
    public boolean tryLock(long timeout) {
        if (isLocked()) {
            LOG.warn("已经上锁:{}", getLockInfo());
            return true;
        }
        //判断是不是无线等待
        boolean infinite = false;
        if (timeout <= 0) {
            infinite = true;
        }
        final long startTime = System.currentTimeMillis();

        refreshLockValue();
        try {
            JpaDatabaseLockDao jpaDatabaseLockDao = SpringUtils.getBean(JpaDatabaseLockDao.class);
            while (true) {
                //判断是否达到超时，不是无限的达到了超时
                if (!infinite && ((System.currentTimeMillis() - startTime) > timeout)) {
                    break;
                }

                long expireTimestamp = getExpireTimestamp();
                boolean succ = jpaDatabaseLockDao.getLock(lockKey, lockValue, expireTimestamp);
                if (succ) {
                    locked = true;
                    break;
                }
                LOG.debug("没有获得锁{}，睡眠等待:{}", getLockInfo(), getTryInterval());
                // 每次请求等待一段时间
                Thread.sleep(getTryInterval());
            }
        } catch (Exception e) {
            LOG.error("", e);
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
    private long getExpireTimestamp() {
        return System.currentTimeMillis() / 1000 + expireTime;
    }

    @Override
    public boolean lock() {
        if (isLocked()) {
            LOG.warn("已经上锁:{}", getLockInfo());
            return true;
        }

        refreshLockValue();
        try {
            JpaDatabaseLockDao jpaDatabaseLockDao = SpringUtils.getBean(JpaDatabaseLockDao.class);
            long expireTimestamp = getExpireTimestamp();
            locked = jpaDatabaseLockDao.getLock(lockKey, lockValue, expireTimestamp);
        } catch (Exception e) {
            LOG.error("", e);
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
        JpaDatabaseLockDao jpaDatabaseLockDao = SpringUtils.getBean(JpaDatabaseLockDao.class);
        boolean succ = jpaDatabaseLockDao.releaseLock(lockKey, lockValue);
        locked = false;
        return !locked;
    }
}
