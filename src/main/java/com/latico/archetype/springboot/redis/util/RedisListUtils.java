package com.latico.archetype.springboot.redis.util;

import org.springframework.data.redis.core.ListOperations;

/**
 * <PRE>
 *     List的操作
 * </PRE>
 *
 * @author: latico
 * @date: 2020-01-02 11:55
 * @version: 1.0
 */
public class RedisListUtils extends AbstractRedisUtils {

    /**
     * 字符串的值操作对象
     */
    private static final ListOperations<String, String> STRING_VALUE_OPERATIONS = RedisTemplateUtils.getStringRedisTemplate().opsForList();
    /**
     * int的值操作
     */
    private static final ListOperations<String, Integer> INT_VALUE_OPERATIONS = RedisTemplateUtils.getIntRedisTemplate().opsForList();
    /**
     * long值操作
     */
    private static final ListOperations<String, Long> LONG_VALUE_OPERATIONS = RedisTemplateUtils.getLongRedisTemplate().opsForList();

    public static ListOperations<String, String> getStringValueOperations() {
        return STRING_VALUE_OPERATIONS;
    }

    public static ListOperations<String, Integer> getIntValueOperations() {
        return INT_VALUE_OPERATIONS;
    }

    public static ListOperations<String, Long> getLongValueOperations() {
        return LONG_VALUE_OPERATIONS;
    }
}
