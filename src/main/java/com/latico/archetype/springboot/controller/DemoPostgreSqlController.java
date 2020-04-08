package com.latico.archetype.springboot.controller;

import com.latico.archetype.springboot.postgresql.entity.PgDemo;
import com.latico.archetype.springboot.postgresql.service.DemoPostgreSqlServicee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <PRE>
 *  演示postgresql的操作
 * </PRE>
 * @author: latico
 * @date: 2020-03-10 11:23:00
 * @version: 1.0
 */
@RestController
@RequestMapping("demo/postgresql")
public class DemoPostgreSqlController {


    @Autowired
    private DemoPostgreSqlServicee demoPostgreSqlServicee;

    @GetMapping("init")
    public void init(){
        demoPostgreSqlServicee.init();
    }

    @GetMapping("queryAll")
    public List<PgDemo> queryAll(){
        return demoPostgreSqlServicee.queryAll();
    }

    @GetMapping("deleteAll")
    public String deleteAll(){
        return demoPostgreSqlServicee.deleteAll();
    }


    @GetMapping("queryByPage")
    public List<PgDemo> queryByPage(){
        return demoPostgreSqlServicee.queryByPage();
    }

}

