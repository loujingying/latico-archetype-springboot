package com.latico.archetype.springboot.redis.util;

import org.springframework.data.redis.core.GeoOperations;

/**
 * <PRE>
 * GEO的操作
 * </PRE>
 *
 * @author: latico
 * @date: 2020-01-02 11:55
 * @version: 1.0
 */
public class RedisGeoUtils extends AbstractRedisUtils {

    /**
     * 字符串的值操作对象
     */
    private static final GeoOperations<String, String> STRING_VALUE_OPERATIONS = RedisTemplateUtils.getStringRedisTemplate().opsForGeo();

    public static GeoOperations<String, String> getStringValueOperations() {
        return STRING_VALUE_OPERATIONS;
    }
}
