package com.latico.archetype.springboot.config.redis;

import com.latico.commons.spring.util.SpringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * <PRE>
 * RedisTemplate的工具类
 * </PRE>
 *
 * @Author: latico
 * @Date: 2020-01-02 11:18
 * @Version: 1.0
 */
public class RedisTemplateUtils {
    /**
     * 通用操作模板，底层自动注册
     */
    private static final RedisTemplate<Object, Object> redisTemplate = SpringUtils.getBean("redisTemplate");
    /**
     * 字符串操作模板，底层自动注册
     */
    private static final StringRedisTemplate stringRedisTemplate = SpringUtils.getBean("stringRedisTemplate");
    /**
     * int值操作模板
     */
    private static final RedisTemplate<String, Integer> intRedisTemplate = SpringUtils.getBean("intRedisTemplate");
    /**
     * long值操作模板
     */
    private static final RedisTemplate<String, Long> longRedisTemplate = SpringUtils.getBean("longRedisTemplate");

    /**
     * @return 通用模板
     */
    public static RedisTemplate<Object, Object> getRedisTemplate() {
        return redisTemplate;
    }

    /**
     * @return 字符串模板，key是字符串，值是字符串
     */
    public static StringRedisTemplate getStringRedisTemplate() {
        return stringRedisTemplate;
    }

    /**
     * @return int模板，key是字符串，值是int
     */
    public static RedisTemplate<String, Integer> getIntRedisTemplate() {
        return intRedisTemplate;
    }

    /**
     * @return long模板，key是字符串，值是long
     */
    public static RedisTemplate<String, Long> getLongRedisTemplate() {
        return longRedisTemplate;
    }

}
