package com.latico.archetype.springboot.controller;

import com.latico.archetype.springboot.common.util.PageableUtils;
import com.latico.archetype.springboot.elasticsearch.entity.DemoElasticsearchBean;
import com.latico.archetype.springboot.elasticsearch.service.DemoElasticsearchService;
import com.latico.archetype.springboot.mongodb.entity.DemoMongoDbBean;
import com.latico.archetype.springboot.mongodb.repository.DemoMongoDbBeanRepository;
import com.latico.archetype.springboot.mongodb.service.DemoMongoDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <PRE>
 *  演示 MongoDB 的操作
 * </PRE>
 * @author: latico
 * @date: 2020-03-10 11:23:00
 * @version: 1.0
 */
@RestController
@RequestMapping("demo/mongodb")
public class DemoMongoDbController {

    @Autowired
    private DemoMongoDbService demoMongoDbService;

    @GetMapping("init")
    public void init(){
        demoMongoDbService.init();
    }

    @GetMapping("queryAll")
    public List<DemoMongoDbBean> queryAll(){
        return demoMongoDbService.queryAll();
    }

    @GetMapping("deleteAll")
    public String deleteAll(){
        return demoMongoDbService.deleteAll();
    }


    @GetMapping("queryByPage")
    public List<DemoMongoDbBean> queryByPage(){
        return demoMongoDbService.queryByPage();
    }

}

