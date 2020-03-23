package com.latico.archetype.springboot.redis.util;

import org.springframework.data.redis.core.ZSetOperations;

/**
 * <PRE>
 *    zset的操作
 * </PRE>
 *
 * @author: latico
 * @date: 2020-01-02 11:55
 * @version: 1.0
 */
public class RedisZsetUtils extends AbstractRedisUtils {

    /**
     * 字符串的值操作对象
     */
    private static final ZSetOperations<String, String> STRING_VALUE_OPERATIONS = RedisTemplateUtils.getStringRedisTemplate().opsForZSet();
    /**
     * int的值操作
     */
    private static final ZSetOperations<String, Integer> INT_VALUE_OPERATIONS = RedisTemplateUtils.getIntRedisTemplate().opsForZSet();
    /**
     * long值操作
     */
    private static final ZSetOperations<String, Long> LONG_VALUE_OPERATIONS = RedisTemplateUtils.getLongRedisTemplate().opsForZSet();

    public static ZSetOperations<String, String> getStringValueOperations() {
        return STRING_VALUE_OPERATIONS;
    }

    public static ZSetOperations<String, Integer> getIntValueOperations() {
        return INT_VALUE_OPERATIONS;
    }

    public static ZSetOperations<String, Long> getLongValueOperations() {
        return LONG_VALUE_OPERATIONS;
    }

}
