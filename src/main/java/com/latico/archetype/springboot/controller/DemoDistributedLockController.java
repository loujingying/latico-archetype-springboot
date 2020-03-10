package com.latico.archetype.springboot.controller;

import com.latico.archetype.springboot.common.lock.DistributedLock;
import com.latico.archetype.springboot.common.lock.impl.db.impl.JpaDatabaseDistributedLockImpl;
import com.latico.archetype.springboot.common.lock.impl.redis.impl.SpringRedisDistributedLockImpl;
import com.latico.archetype.springboot.redis.util.RedisTemplateUtils;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <PRE>
 *
 测试时，可以在redis手工创建对应的列表等
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-12-31 16:34
 * @Version: 1.0
 */
@RestController
@RequestMapping("demo/lock")
public class DemoDistributedLockController {

    private static final Logger LOG = LoggerFactory.getLogger(DemoDistributedLockController.class);

    /**
     * @param lockKey 锁的key
     * @param execTime 执行时间
     * @param expireTime 过期时间
     * @param timeout 拿锁的超时时间
     * @param unlock     是否解锁，为了测试不解锁时的超时自动解锁情况
     * @return
     */
    @RequestMapping(value = "testRedisLock")
    public Boolean testRedisLock(String lockKey, long execTime, int expireTime, long timeout, boolean unlock) {
        DistributedLock redisLock = new SpringRedisDistributedLockImpl(RedisTemplateUtils.getStringRedisTemplate(), lockKey, expireTime);
        boolean lock = redisLock.tryLock(timeout);
        LOG.info("拿到锁状态:{}, {}", lock, redisLock);
        try {
            Thread.sleep(execTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (unlock) {
            LOG.info("解锁:{} {}", redisLock, redisLock.unlock());
        } else {
            LOG.info("不解锁:{}", redisLock);
        }
        return true;
    }

    /**
     * @param lockKey 锁的key
     * @param execTime 执行时间
     * @param expireTime 过期时间
     * @param timeout 拿锁的超时时间
     * @param unlock     是否解锁，为了测试不解锁时的超时自动解锁情况
     * @return
     */
    @RequestMapping(value = "testDbLock")
    public Boolean testDbLock(String lockKey, long execTime, int expireTime, long timeout, boolean unlock) {
        DistributedLock redisLock = new JpaDatabaseDistributedLockImpl(lockKey, expireTime);
        boolean lock = redisLock.tryLock(timeout);
        LOG.info("拿到锁状态:{}, {}", lock, redisLock);
        try {
            Thread.sleep(execTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (unlock) {
            LOG.info("解锁:{} {}", redisLock, redisLock.unlock());
        } else {
            LOG.info("不解锁:{}", redisLock);
        }
        return true;
    }

}
