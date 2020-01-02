package com.latico.archetype.springboot.common.redis;


import com.latico.archetype.springboot.config.redis.RedisTemplateUtils;

/**
 * <PRE>
 * GEO的操作
 * </PRE>
 *
 * @Author: latico
 * @Date: 2020-01-02 11:55
 * @Version: 1.0
 */
public class RedisGeoUtils extends AbstractRedisUtils {

    /**
     * 字符串的值操作对象
     */
    private static final org.springframework.data.redis.core.GeoOperations<String, String> stringValueOperations = RedisTemplateUtils.getStringRedisTemplate().opsForGeo();

    public static org.springframework.data.redis.core.GeoOperations<String, String> getStringValueOperations() {
        return stringValueOperations;
    }
}
