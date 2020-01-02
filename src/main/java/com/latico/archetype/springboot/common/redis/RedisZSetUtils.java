package com.latico.archetype.springboot.common.redis;

import com.latico.archetype.springboot.config.redis.RedisTemplateUtils;
import org.springframework.data.redis.core.ZSetOperations;

/**
 * <PRE>
 *    zset的操作
 * </PRE>
 *
 * @Author: latico
 * @Date: 2020-01-02 11:55
 * @Version: 1.0
 */
public class RedisZSetUtils extends AbstractRedisUtils {

    /**
     * 字符串的值操作对象
     */
    private static final org.springframework.data.redis.core.ZSetOperations<String, String> stringValueOperations = RedisTemplateUtils.getStringRedisTemplate().opsForZSet();
    /**
     * int的值操作
     */
    private static final org.springframework.data.redis.core.ZSetOperations<String, Integer> intValueOperations = RedisTemplateUtils.getIntRedisTemplate().opsForZSet();
    /**
     * long值操作
     */
    private static final org.springframework.data.redis.core.ZSetOperations<String, Long> longValueOperations = RedisTemplateUtils.getLongRedisTemplate().opsForZSet();

    public static ZSetOperations<String, String> getStringValueOperations() {
        return stringValueOperations;
    }

    public static ZSetOperations<String, Integer> getIntValueOperations() {
        return intValueOperations;
    }

    public static ZSetOperations<String, Long> getLongValueOperations() {
        return longValueOperations;
    }

}
