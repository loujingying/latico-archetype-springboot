package com.latico.archetype.springboot.controller;

import com.latico.archetype.springboot.dao.primary.entity.Demo;
import com.latico.archetype.springboot.dao.primary.mapper.DemoDaoRepository;
import com.latico.archetype.springboot.dao.primary.mapper.DemoRepository;
import com.latico.archetype.springboot.dao.secondary.entity.Demo2;
import com.latico.archetype.springboot.dao.secondary.mapper.Demo2Mapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <PRE>
 * 测试
 * 数据库查询接口等
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-03-10 16:13
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "test")
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Api(description = "测试数据库API")
public class TestDBController {

    /**
     * 测试JPA方式
     */
    @Autowired
    DemoRepository demoRepository;

    /**
     * 测试Mybatis方式
     */
    @Autowired
    Demo2Mapper demo2Mapper;


    @Autowired
    DemoDaoRepository demoDaoRepository;

    /**
     * @return 查询数据库所有Demo数据
     */
    @RequestMapping(value = "selectDemo")
    @ApiOperation("测试查询Demo表API")
    public List<Demo> selectDemo() {

        List<Demo> list = new ArrayList<>();

        Demo demo = new Demo();
        list.add(demo);
        demo.setAdministrator(true);
        demo.setPassword("admin");
        demo.setUsername("admin");
        demo = new Demo();
        list.add(demo);
        demo.setAdministrator(true);
        demo.setPassword("admin");
        demo.setUsername("admin");
        demo = new Demo();
        list.add(demo);
        demo.setAdministrator(true);
        demo.setPassword("admin");
        demo.setUsername("admin");
        demoDaoRepository.insertBatch(list, 500);

        List<Demo> all = demoRepository.findAll();

        Iterable<Demo> demos = demoDaoRepository.updateBatch(all, 500);

        return all;


    }

    @RequestMapping(value = "selectDemo2")
    @ApiOperation("测试查询Demo2表API")
    public List<Demo2> selectDemo2() {
        return demo2Mapper.findAll();
    }


}
