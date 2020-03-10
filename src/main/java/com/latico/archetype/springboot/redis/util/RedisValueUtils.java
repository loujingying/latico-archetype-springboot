package com.latico.archetype.springboot.redis.util;

import org.springframework.data.redis.core.ValueOperations;

/**
 * <PRE>
 * 普通的操作
 * </PRE>
 *
 * @Author: latico
 * @Date: 2020-01-02 11:55
 * @Version: 1.0
 */
public class RedisValueUtils extends AbstractRedisUtils {

    /**
     * 字符串的值操作对象
     */
    private static final org.springframework.data.redis.core.ValueOperations<String, String> stringValueOperations = RedisTemplateUtils.getStringRedisTemplate().opsForValue();
    /**
     * int的值操作
     */
    private static final org.springframework.data.redis.core.ValueOperations<String, Integer> intValueOperations = RedisTemplateUtils.getIntRedisTemplate().opsForValue();
    /**
     * long值操作
     */
    private static final org.springframework.data.redis.core.ValueOperations<String, Long> longValueOperations = RedisTemplateUtils.getLongRedisTemplate().opsForValue();

    public static ValueOperations<String, String> getStringValueOperations() {
        return stringValueOperations;
    }

    public static ValueOperations<String, Integer> getIntValueOperations() {
        return intValueOperations;
    }

    public static ValueOperations<String, Long> getLongValueOperations() {
        return longValueOperations;
    }

    /**
     * get一个值
     *
     * @param key
     * @return 返回字符串格式值
     */
    public static String get(String key) {
        return stringValueOperations.get(key);
    }

    /**
     * get一个值，int类型返回
     *
     * @param key
     * @return 返回Integer类型的值
     */
    public static Integer getInt(String key) {
        return intValueOperations.get(key);
    }

    public static Long getLong(String key) {
        return longValueOperations.get(key);
    }

    public static void set(String key, String value) {
        stringValueOperations.set(key, value);
    }

    public static Boolean setIfAbsent(String key, String value) {
        return stringValueOperations.setIfAbsent(key, value);
    }

}
