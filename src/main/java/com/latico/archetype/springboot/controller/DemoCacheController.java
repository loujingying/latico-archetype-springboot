package com.latico.archetype.springboot.controller;

import com.latico.archetype.springboot.cache.dao.impl.DemoSpringCacheDaoImpl;
import com.latico.archetype.springboot.dao.primary.entity.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <PRE>
 *
 * </PRE>
 * @author: latico
 * @date: 2020-03-27 10:27:40
 * @version: 1.0
 */
@RestController
@RequestMapping("demo/cache")
public class DemoCacheController {

    @Autowired
    private DemoSpringCacheDaoImpl demoSpringCacheServiceImpl;

    @GetMapping("getCacheKeys")
    public List<Integer> getCacheKeys(){
        return demoSpringCacheServiceImpl.getCacheKeys();
    }

    @GetMapping("cacheable")
    public Demo cacheable(@RequestParam int id){
        return demoSpringCacheServiceImpl.cacheable(id);
    }

    @PostMapping("put")
    public boolean put(@RequestBody Demo demo){
        demoSpringCacheServiceImpl.put(demo.getTaskId(), demo);
        return true;
    }

    @GetMapping("evict")
    public boolean evict(@RequestParam int id){
        demoSpringCacheServiceImpl.evict(id);
        return true;
    }

    @GetMapping("clean")
    public boolean clean(){
        demoSpringCacheServiceImpl.clean();
        return true;
    }
    
}