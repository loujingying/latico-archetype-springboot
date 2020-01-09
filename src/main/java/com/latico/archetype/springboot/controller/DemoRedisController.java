package com.latico.archetype.springboot.controller;

import com.latico.archetype.springboot.config.redis.util.*;
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

}
