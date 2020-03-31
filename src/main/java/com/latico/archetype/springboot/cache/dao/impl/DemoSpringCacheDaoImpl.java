package com.latico.archetype.springboot.cache.dao.impl;

import com.latico.archetype.springboot.cache.config.SpringCacheConfig;
import com.latico.archetype.springboot.cache.dao.AbstractSpringCacheDao;
import com.latico.archetype.springboot.cache.dao.SpringCacheDao;
import com.latico.archetype.springboot.dao.primary.entity.Demo;
import com.latico.archetype.springboot.dao.primary.repository.DemoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <PRE>
 * 示例缓存的使用
 * </PRE>
 *
 * @author: latico
 * @date: 2020-03-27 10:35
 * @version: 1.0
 */
@Service
@CacheConfig(cacheNames = DemoSpringCacheDaoImpl.CACHE_NAME)
@Slf4j
public class DemoSpringCacheDaoImpl extends AbstractSpringCacheDao<Demo, Integer> {

    /**
     * 本缓存的名字
     */
    public static final String CACHE_NAME = SpringCacheConfig.CACHE_NAME_DEMO;

    @Autowired
    private DemoRepository demoRepository;


    @Override
    public String getCacheName() {
        return CACHE_NAME;
    }

    @Override
    public Demo cacheable(Integer id) {
        log.info("缓存不存在, 通过id:{}查询数据", id);
        Demo demo = demoRepository.queryFirstByTaskId(id);
        log.info("从数据库查询出:{}", demo);
        return demo;
    }

    @Override
    public Demo cacheable(Integer id, Demo data) {
        log.info("缓存不存在, 通过id:{}查询数据", id);
        Demo demo = demoRepository.queryFirstByTaskId(data.getTaskId());
        log.info("从数据库查询出:{}", demo);
        return demo;
    }

    @Override
    public Demo put(Integer id) {
        return super.put(id);
    }

    @Override
    public Demo put(Integer id, Demo data) {
        return super.put(id, data);
    }

    @Override
    public void evict(Integer id) {
        super.evict(id);
    }

    @Override
    public void evict(Integer id, Demo data) {
        super.evict(id, data);
    }

    @Override
    public void clean() {
        super.clean();
    }
}
