package com.latico.archetype.springboot.redis.util;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @author: latico
 * @date: 2020-01-02 14:45
 * @version: 1.0
 */
public abstract class AbstractRedisUtils {
    /**
     * 字符串的值操作对象
     */
    private static final StringRedisTemplate STRING_REDIS_TEMPLATE = RedisTemplateUtils.getStringRedisTemplate();

    /**
     * 过期时长：60分钟，单位：秒
     */
    public final static long ONE_HOUR_EXPIRE_SECONDS = 60 * 60;
    /**
     * 过期时长：1天，单位：秒
     */
    public final static long ONE_DAY_EXPIRE_SECONDS = 60 * 60 * 24;
    /**
     * 不设置过期时长
     */
    public final static long NOT_EXPIRE = -1;


    /**
     * 删除数据
     *
     * @param key
     */
    public static Boolean delete(String key) {
        return STRING_REDIS_TEMPLATE.delete(key);
    }

    /**
     * 设置秒级超时
     * @param key
     * @param timeInSecond
     * @return
     */
    public static boolean expire(String key, long timeInSecond) {
        return STRING_REDIS_TEMPLATE.expire(key, timeInSecond, TimeUnit.SECONDS);
    }

    /**
     * 获取超时时间
     * @param key
     * @return
     */
    public static Long getExpire(String key) {
        return STRING_REDIS_TEMPLATE.getExpire(key);
    }

}
