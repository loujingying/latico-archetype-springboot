package com.latico.archetype.springboot.cache.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * <PRE>
 * 加@EnableCaching注解，启动spring缓存功能
 *
 * 缓存功能可以使用的组合为：
 * 1、Spring Cache + Redis
 * 2、Spring Cache + Ehcache
 *
 * 在个骨架中，我们默认使用：Spring Cache + Ehcache
 * </PRE>
 *
 * @author: latico
 * @date: 2020-03-26 17:56
 * @version: 1.0
 */
@Configuration
@EnableCaching
public class SpringCacheConfig {
    /**
     * 某一个缓存的名字，在ehcache.xml中配置的demo缓存
     */
    public static final String CACHE_NAME = "demo";
}
