package com.latico.archetype.springboot.redis.util;

import com.latico.commons.spring.util.SpringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * <PRE>
 * RedisTemplate的工具类
 * </PRE>
 *
 * @author: latico
 * @date: 2020-01-02 11:18
 * @version: 1.0
 */
public class RedisTemplateUtils {
    /**
     * 通用操作模板，底层自动注册
     */
    private static final RedisTemplate<Object, Object> REDIS_TEMPLATE = SpringUtils.getBean("redisTemplate");
    /**
     * 字符串操作模板，底层自动注册
     */
    private static final StringRedisTemplate STRING_REDIS_TEMPLATE = SpringUtils.getBean("stringRedisTemplate");
    /**
     * int值操作模板
     */
    private static final RedisTemplate<String, Integer> INT_REDIS_TEMPLATE = SpringUtils.getBean("intRedisTemplate");
    /**
     * long值操作模板
     */
    private static final RedisTemplate<String, Long> LONG_REDIS_TEMPLATE = SpringUtils.getBean("longRedisTemplate");

    /**
     * @return 通用模板
     */
    public static RedisTemplate<Object, Object> getRedisTemplate() {
        return REDIS_TEMPLATE;
    }

    /**
     * @return 字符串模板，key是字符串，值是字符串
     */
    public static StringRedisTemplate getStringRedisTemplate() {
        return STRING_REDIS_TEMPLATE;
    }

    /**
     * @return int模板，key是字符串，值是int
     */
    public static RedisTemplate<String, Integer> getIntRedisTemplate() {
        return INT_REDIS_TEMPLATE;
    }

    /**
     * @return long模板，key是字符串，值是long
     */
    public static RedisTemplate<String, Long> getLongRedisTemplate() {
        return LONG_REDIS_TEMPLATE;
    }

}
