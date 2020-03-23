package com.latico.archetype.springboot.redis.util;

import org.springframework.data.redis.core.ValueOperations;

/**
 * <PRE>
 * 普通的操作
 * </PRE>
 *
 * @author: latico
 * @date: 2020-01-02 11:55
 * @version: 1.0
 */
public class RedisValueUtils extends AbstractRedisUtils {

    /**
     * 字符串的值操作对象
     */
    private static final ValueOperations<String, String> STRING_VALUE_OPERATIONS = RedisTemplateUtils.getStringRedisTemplate().opsForValue();
    /**
     * int的值操作
     */
    private static final ValueOperations<String, Integer> INT_VALUE_OPERATIONS = RedisTemplateUtils.getIntRedisTemplate().opsForValue();
    /**
     * long值操作
     */
    private static final ValueOperations<String, Long> LONG_VALUE_OPERATIONS = RedisTemplateUtils.getLongRedisTemplate().opsForValue();

    public static ValueOperations<String, String> getStringValueOperations() {
        return STRING_VALUE_OPERATIONS;
    }

    public static ValueOperations<String, Integer> getIntValueOperations() {
        return INT_VALUE_OPERATIONS;
    }

    public static ValueOperations<String, Long> getLongValueOperations() {
        return LONG_VALUE_OPERATIONS;
    }

    /**
     * get一个值
     *
     * @param key
     * @return 返回字符串格式值
     */
    public static String get(String key) {
        return STRING_VALUE_OPERATIONS.get(key);
    }

    /**
     * get一个值，int类型返回
     *
     * @param key
     * @return 返回Integer类型的值
     */
    public static Integer getInt(String key) {
        return INT_VALUE_OPERATIONS.get(key);
    }

    public static Long getLong(String key) {
        return LONG_VALUE_OPERATIONS.get(key);
    }

    public static void set(String key, String value) {
        STRING_VALUE_OPERATIONS.set(key, value);
    }

    public static Boolean setIfAbsent(String key, String value) {
        return STRING_VALUE_OPERATIONS.setIfAbsent(key, value);
    }

}
