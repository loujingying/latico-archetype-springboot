package com.latico.archetype.springboot.config.redis.util;

import com.latico.archetype.springboot.config.redis.RedisTemplateUtils;
import org.springframework.data.redis.core.ListOperations;

/**
 * <PRE>
 *     List的操作
 * </PRE>
 *
 * @Author: latico
 * @Date: 2020-01-02 11:55
 * @Version: 1.0
 */
public class RedisListUtils extends AbstractRedisUtils {

    /**
     * 字符串的值操作对象
     */
    private static final org.springframework.data.redis.core.ListOperations<String, String> stringValueOperations = RedisTemplateUtils.getStringRedisTemplate().opsForList();
    /**
     * int的值操作
     */
    private static final org.springframework.data.redis.core.ListOperations<String, Integer> intValueOperations = RedisTemplateUtils.getIntRedisTemplate().opsForList();
    /**
     * long值操作
     */
    private static final org.springframework.data.redis.core.ListOperations<String, Long> longValueOperations = RedisTemplateUtils.getLongRedisTemplate().opsForList();

    public static ListOperations<String, String> getStringValueOperations() {
        return stringValueOperations;
    }

    public static ListOperations<String, Integer> getIntValueOperations() {
        return intValueOperations;
    }

    public static ListOperations<String, Long> getLongValueOperations() {
        return longValueOperations;
    }
}
