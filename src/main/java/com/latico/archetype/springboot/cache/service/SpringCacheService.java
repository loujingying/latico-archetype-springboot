package com.latico.archetype.springboot.cache.service;

import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * <PRE>
 *
 * </PRE>
 * @param <T> 数据的类型
 * @param <K> ID的类型
 * @author: latico
 * @date: 2020-03-27 10:35
 * @version: 1.0
 */
public interface SpringCacheService<T, K> {

    /**
     * 把数据进行缓存
     * 从缓存中获取key是ID的数据，
     * 如果获取到了，就直接返回缓存中的数据，不会执行方法体
     * 获取不到，就执行方法体，并把方法体的数据放进缓存，
     * @param id 缓存的key,必须输入
     * @return 返回值会入到缓存中
     */
    @Cacheable(key = "#id")
    T cacheable(K id);

    /**
     * 把数据进行缓存
     * 从缓存中获取key是ID的数据，
     * 如果获取到了，就直接返回缓存中的数据，不会执行方法体
     * 获取不到，就执行方法体，并把方法体的数据放进缓存，
     * @param id 缓存的key,必须输入
     * @param data 这里把数据对象也放进来，目的是假如id字段是多个字段的拼接字段，那么这个data方便的传入各个所需字段，方便库查询操作
     * @return 返回值会入到缓存中
     */
    @Cacheable(key = "#id")
    T cacheable(K id, T data);

    /**
     * 更新缓存数据
     * 1、执行方法体（可以在方法体进行数据库记录更新操作，但是不建议）；
     * 2、把方法体的返回值放进缓存中，如果缓存中已经存在数据，就会更新；
     * @param id 存的key,必须输入
     * @param data 可以为空
     * @return 返回值会强制更新进缓存中，如果缓存没有就是插入，有就更新
     */
    @CachePut(key = "#id")
    T put(K id, T data);

    /**
     * 根据id删除缓存单个数据
     * 1、执行方法体（可以在方法体进行数据库删除记录操作，但是不建议）；
     * 2、把方法体的返回值放进缓存中，如果缓存中已经存在数据，就会更新；
     * @param id 存的key,必须输入，
     * @param data 可以为空
     * @return
     */
    @CacheEvict(key = "#id", allEntries = false)
    void evict(K id, T data);

    /**
     * 清空缓存中所有数据
     * 执行方法体（可以在方法体进行数据库记录清理操作，但是不建议）
     * @return
     */
    @CacheEvict(allEntries = true)
    void clean();

}
