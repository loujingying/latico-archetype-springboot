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
 * @author: latico
 * @date: 2020-01-10 17:30
 * @version: 1.0
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
    @Transactional(rollbackFor = Exception.class)
    public boolean getLock(String lockKey, String lockValue, long expireTime) {
        DatabaseLock databaseLock = databaseLockRepository.queryByLockKey(lockKey);
        if (databaseLock == null) {
            LOG.warn("获取锁失败，数据库没有该锁:{}", lockKey);
            return false;
        }

        //锁已经被获取，但是检测是否过期
        if (1 == databaseLock.getLockStatus()) {
            if (databaseLock.getExpireTime() != null && databaseLock.getExpireTime() >= 1) {
                long currentTimeSeconds = System.currentTimeMillis() / 1000;
                if (currentTimeSeconds > databaseLock.getExpireTime()) {
                    LOG.info("本次能获得锁，因为数据库的锁过期:{}", databaseLock);

                } else {
                    LOG.warn("获取锁失败，该锁被使用中并且未过期:{}", databaseLock);
                    return false;
                }
            } else {
                LOG.warn("获取锁失败，该锁被使用中，并且该锁没有过期时间:{}", databaseLock);
                return false;
            }

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
    @Transactional(rollbackFor = Exception.class)
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
