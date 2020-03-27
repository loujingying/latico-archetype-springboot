package com.latico.archetype.springboot.controller;

import com.latico.archetype.springboot.cache.service.impl.DemoSpringCacheServiceImpl;
import com.latico.archetype.springboot.dao.primary.entity.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private DemoSpringCacheServiceImpl demoCacheServiceImpl;

    @GetMapping("cacheable")
    public Demo cacheable(@RequestParam int id){
        return demoCacheServiceImpl.cacheable(id);
    }

    @PostMapping("put")
    public boolean put(@RequestBody Demo demo){
        demoCacheServiceImpl.put(demo.getTaskId(), demo);
        return true;
    }

    @GetMapping("evict")
    public boolean evict(@RequestParam int id){
        demoCacheServiceImpl.evict(id, null);
        return true;
    }

    @GetMapping("clean")
    public boolean clean(){
        demoCacheServiceImpl.clean();
        return true;
    }
    
}