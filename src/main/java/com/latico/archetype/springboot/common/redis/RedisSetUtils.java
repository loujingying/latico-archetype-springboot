package com.latico.archetype.springboot.common.redis;

import com.latico.archetype.springboot.config.redis.RedisTemplateUtils;
import org.springframework.data.redis.core.SetOperations;

/**
 * <PRE>
 *    set的操作
 * </PRE>
 *
 * @Author: latico
 * @Date: 2020-01-02 11:55
 * @Version: 1.0
 */
public class RedisSetUtils extends AbstractRedisUtils {

    /**
     * 字符串的值操作对象
     */
    private static final org.springframework.data.redis.core.SetOperations<String, String> stringValueOperations = RedisTemplateUtils.getStringRedisTemplate().opsForSet();
    /**
     * int的值操作
     */
    private static final org.springframework.data.redis.core.SetOperations<String, Integer> intValueOperations = RedisTemplateUtils.getIntRedisTemplate().opsForSet();
    /**
     * long值操作
     */
    private static final org.springframework.data.redis.core.SetOperations<String, Long> longValueOperations = RedisTemplateUtils.getLongRedisTemplate().opsForSet();

    public static SetOperations<String, String> getStringValueOperations() {
        return stringValueOperations;
    }

    public static SetOperations<String, Integer> getIntValueOperations() {
        return intValueOperations;
    }

    public static SetOperations<String, Long> getLongValueOperations() {
        return longValueOperations;
    }
}
