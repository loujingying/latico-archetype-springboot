package com.latico.archetype.springboot.controller;

import com.latico.archetype.springboot.common.lock.DistributedLock;
import com.latico.archetype.springboot.common.lock.impl.redis.impl.SpringRedisDistributedLockImpl;
import com.latico.archetype.springboot.redis.util.RedisTemplateUtils;
import com.latico.archetype.springboot.redis.util.*;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

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
@RequestMapping("demo/redis")
public class DemoRedisController {

    private static final Logger LOG = LoggerFactory.getLogger(DemoRedisController.class);
    @RequestMapping(value = "get")
    public String get(@RequestParam("key") String key) {
        return RedisValueUtils.get(key);
    }

    @RequestMapping(value = "set")
    public boolean set(String key, String value) {
        RedisValueUtils.set(key, value);
        return true;
    }

    @RequestMapping(value = "list/index")
    public String listIndex(String listkey, long index) {
        return RedisListUtils.getStringValueOperations().index(listkey, index);
    }

    @RequestMapping(value = "list/range")
    public List<String> listRange(String listkey, long indexStart, long indexEnd) {
        return RedisListUtils.getStringValueOperations().range(listkey, indexStart, indexEnd);
    }

    @RequestMapping(value = "list/rightPush")
    public Long listRightPush(String listkey, String value) {
        return RedisListUtils.getStringValueOperations().rightPush(listkey, value);
    }

    @RequestMapping(value = "zset/add")
    public Boolean listRightPush(String key, String value, double score) {
        return RedisZSetUtils.getStringValueOperations().add(key, value, score);
    }

    @RequestMapping(value = "zset/range")
    public Set<String> zsetRange(String zsetkey, long indexStart, long indexEnd) {
        return RedisZSetUtils.getStringValueOperations().range(zsetkey, indexStart, indexEnd);
    }

    @RequestMapping(value = "zset/rangeByScore")
    public Set<String> zsetRangeByScore(String zsetkey, double minScore, double maxScore) {
        return RedisZSetUtils.getStringValueOperations().rangeByScore(zsetkey, minScore, maxScore);
    }

    @RequestMapping(value = "set/add")
    public Long setAdd(String key, String value) {
        return RedisSetUtils.getStringValueOperations().add(key, value);
    }

    @RequestMapping(value = "set/isMember")
    public Boolean setIsMember(String key, String value) {
        return RedisSetUtils.getStringValueOperations().isMember(key, value);
    }

    @RequestMapping(value = "hash/get")
    public String get(String key, String hashKey) {
        return RedisHashUtils.getStringValueOperations().get(key, hashKey);
    }

    @RequestMapping(value = "hash/put")
    public Boolean hashPut(String key, String hashKey, String hashValue) {
        RedisHashUtils.getStringValueOperations().put(key, hashKey, hashValue);
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
    @RequestMapping(value = "testLock")
    public Boolean testLock(String lockKey, long execTime, int expireTime, long timeout, boolean unlock) {
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

}
