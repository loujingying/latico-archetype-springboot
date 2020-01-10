package com.latico.archetype.springboot.common.lock.impl.db.dao;

import com.latico.archetype.springboot.dao.primary.entity.DatabaseLock;
import com.latico.archetype.springboot.dao.primary.repository.DatabaseLockRepository;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @Author: latico
 * @Date: 2020-01-10 17:30
 * @Version: 1.0
 */
@Repository
public class JpaDatabaseLockDao {
    private static final Logger LOG = LoggerFactory.getLogger(JpaDatabaseLockDao.class);
    @Autowired
    private DatabaseLockRepository databaseLockRepository;


    /**
     * 用事务的方式进行查询和更新
     * @param lockKey
     * @param lockValue
     * @param expireTime
     * @return
     */
    @Transactional
    public boolean getLock(String lockKey, String lockValue, long expireTime) {
        DatabaseLock databaseLock = databaseLockRepository.queryByLockKey(lockKey);
        if (databaseLock == null) {
            LOG.warn("获取锁失败，数据库没有该锁:{}", lockKey);
            return false;
        }

        //锁已经被获取
        if (1 == databaseLock.getLockStatus()) {
            LOG.warn("获取锁失败，该锁被使用中:{}", databaseLock);
            return false;
        }

        databaseLock.setLockStatus(1);
        databaseLock.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        databaseLock.setLockValue(lockValue);
        databaseLock.setExpireTime(expireTime);

        databaseLockRepository.save(databaseLock);
        LOG.info("获取锁成功:{}", databaseLock);
        return true;
    }

    /**
     * 释放锁
     * @param lockKey
     * @return
     */
    @Transactional
    public boolean releaseLock(String lockKey, String lockValue) {
        DatabaseLock databaseLock = databaseLockRepository.queryByLockKey(lockKey);
        if (databaseLock == null) {
            LOG.warn("释放锁失败，数据库没有该锁:{}", lockKey);
            return false;
        }

        //锁未被获取
        if (0 == databaseLock.getLockStatus()) {
            LOG.warn("不需要释放锁，该锁没有被使用:{}", databaseLock);
            return false;
        }

        if (!lockValue.equals(databaseLock.getLockValue())) {
            LOG.warn("不能释放锁，该锁的值不匹配，释放值:{}, 数据库当前信息:{}", lockValue, databaseLock);
            return false;
        }

        databaseLock.setLockStatus(0);
        databaseLock.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        databaseLockRepository.save(databaseLock);
        LOG.info("释放锁成功:{}", databaseLock);
        return true;
    }

}
