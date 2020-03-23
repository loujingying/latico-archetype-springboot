package com.latico.archetype.springboot.redis.util;

import org.springframework.data.redis.core.HashOperations;

/**
 * <PRE>
 *    hash的操作
 * </PRE>
 *
 * @author: latico
 * @date: 2020-01-02 11:55
 * @version: 1.0
 */
public class RedisHashUtils extends AbstractRedisUtils {

    /**
     * 字符串的值操作对象
     */
    private static final HashOperations<String, String, String> STRING_VALUE_OPERATIONS = RedisTemplateUtils.getStringRedisTemplate().opsForHash();
    /**
     * int的值操作
     */
    private static final HashOperations<String, String, Integer> INT_VALUE_OPERATIONS = RedisTemplateUtils.getIntRedisTemplate().opsForHash();
    /**
     * long值操作
     */
    private static final HashOperations<String, String, Long> LONG_VALUE_OPERATIONS = RedisTemplateUtils.getLongRedisTemplate().opsForHash();

    public static HashOperations<String, String, String> getStringValueOperations() {
        return STRING_VALUE_OPERATIONS;
    }

    public static HashOperations<String, String, Integer> getIntValueOperations() {
        return INT_VALUE_OPERATIONS;
    }

    public static HashOperations<String, String, Long> getLongValueOperations() {
        return LONG_VALUE_OPERATIONS;
    }
}
