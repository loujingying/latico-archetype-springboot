package com.latico.archetype.springboot.controller;

import com.latico.archetype.springboot.elasticsearch.entity.DemoElasticSearchBean;
import com.latico.archetype.springboot.elasticsearch.service.DemoElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <PRE>
 *  演示elasticsearch的操作
 * </PRE>
 * @author: latico
 * @date: 2020-03-10 11:23:00
 * @version: 1.0
 */
@RestController
@RequestMapping("/demo/elasticsearch")
public class DemoElasticsearchController {

    @Autowired
    private DemoElasticSearchService demoElasticsearchService;

    @GetMapping("/init")
    public void init(){
        demoElasticsearchService.createIndex();
        List<DemoElasticSearchBean> list =new ArrayList<>();
        list.add(new DemoElasticSearchBean("1","XX0193","XX8064","内容1",1));
        list.add(new DemoElasticSearchBean("2","XX0210","XX7475","内容2",1));
        list.add(new DemoElasticSearchBean("3","XX0257","XX8097","内容3",1));
        demoElasticsearchService.saveAll(list);

    }

    @GetMapping("/all")
    public Iterator<DemoElasticSearchBean> all(){
        return demoElasticsearchService.findAll();
    }

}

