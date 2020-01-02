package com.latico.archetype.springboot.common.redis;

import com.latico.archetype.springboot.config.redis.RedisTemplateUtils;
import org.springframework.data.redis.core.HashOperations;

/**
 * <PRE>
 *    hash的操作
 * </PRE>
 *
 * @Author: latico
 * @Date: 2020-01-02 11:55
 * @Version: 1.0
 */
public class RedisHashUtils extends AbstractRedisUtils {

    /**
     * 字符串的值操作对象
     */
    private static final org.springframework.data.redis.core.HashOperations<String, String, String> stringValueOperations = RedisTemplateUtils.getStringRedisTemplate().opsForHash();
    /**
     * int的值操作
     */
    private static final org.springframework.data.redis.core.HashOperations<String, String, Integer> intValueOperations = RedisTemplateUtils.getIntRedisTemplate().opsForHash();
    /**
     * long值操作
     */
    private static final org.springframework.data.redis.core.HashOperations<String, String, Long> longValueOperations = RedisTemplateUtils.getLongRedisTemplate().opsForHash();

    public static HashOperations<String, String, String> getStringValueOperations() {
        return stringValueOperations;
    }

    public static HashOperations<String, String, Integer> getIntValueOperations() {
        return intValueOperations;
    }

    public static HashOperations<String, String, Long> getLongValueOperations() {
        return longValueOperations;
    }
}
