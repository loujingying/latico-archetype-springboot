package com.latico.archetype.springboot.cache.service.impl;

import com.latico.archetype.springboot.cache.config.SpringCacheConfig;
import com.latico.archetype.springboot.cache.service.SpringCacheService;
import com.latico.archetype.springboot.dao.primary.entity.Demo;
import com.latico.archetype.springboot.dao.primary.repository.DemoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @author: latico
 * @date: 2020-03-27 10:35
 * @version: 1.0
 */
@Service
@CacheConfig(cacheNames = DemoSpringCacheServiceImpl.CACHE_NAME)
@Slf4j
public class DemoSpringCacheServiceImpl implements SpringCacheService<Demo, Integer> {

    /**
     * 本缓存的名字
     */
    public static final String CACHE_NAME = SpringCacheConfig.CACHE_NAME;

    @Autowired
    private DemoRepository demoRepository;

    @Override
    public Demo cacheable(Integer id) {
        log.info("通过id:{}查询获取", id);
        Demo demo = demoRepository.queryFirstByTaskId(id);
        log.info("从数据库查询出:{}", demo);
        return demo;
    }

    @Override
    public Demo put(Integer id, Demo data) {
        log.info("更新id:{},数据:{}", id, data);
        // 更新的时候，不是真的从数据库更新，也可以同时在这里从数据库中更新
        // demoRepository.save(data);
        return data;
    }

    @Override
    public void evict(Integer id, Demo data) {
        log.info("删除id:{},数据:{}", id, data);
        // 删除的时候，不是真的从数据库删除，也可以同时在这里从数据库中删除
        // demoRepository.deleteByTaskId(id);
    }

    @Override
    public void clean() {
        log.info("清空缓存:{} 所有数据", CACHE_NAME);
        //删除的时候，不是真的从数据库删除，也可以同时在这里从数据库中删除
        // demoRepository.deleteAll();
    }
}
