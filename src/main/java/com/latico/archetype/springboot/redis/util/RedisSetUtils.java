package com.latico.archetype.springboot.redis.util;

import org.springframework.data.redis.core.SetOperations;

/**
 * <PRE>
 *    set的操作
 * </PRE>
 *
 * @author: latico
 * @date: 2020-01-02 11:55
 * @version: 1.0
 */
public class RedisSetUtils extends AbstractRedisUtils {

    /**
     * 字符串的值操作对象
     */
    private static final SetOperations<String, String> STRING_VALUE_OPERATIONS = RedisTemplateUtils.getStringRedisTemplate().opsForSet();
    /**
     * int的值操作
     */
    private static final SetOperations<String, Integer> INT_VALUE_OPERATIONS = RedisTemplateUtils.getIntRedisTemplate().opsForSet();
    /**
     * long值操作
     */
    private static final SetOperations<String, Long> LONG_VALUE_OPERATIONS = RedisTemplateUtils.getLongRedisTemplate().opsForSet();

    public static SetOperations<String, String> getStringValueOperations() {
        return STRING_VALUE_OPERATIONS;
    }

    public static SetOperations<String, Integer> getIntValueOperations() {
        return INT_VALUE_OPERATIONS;
    }

    public static SetOperations<String, Long> getLongValueOperations() {
        return LONG_VALUE_OPERATIONS;
    }
}
