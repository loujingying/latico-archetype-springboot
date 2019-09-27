package com.latico.archetype.springboot.dao.primary.mapper;

import com.github.pagehelper.Page;
import com.latico.archetype.springboot.config.DbConfig;
import com.latico.archetype.springboot.dao.DaoTestApplication;
import com.latico.archetype.springboot.dao.primary.entity.Demo;
import com.latico.commons.orm.mybatis.PageHelperUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DaoTestApplication.class, DbConfig.class})
public class DemoMapperTest {
    @Autowired
    DemoMapper demoMapper;

    @Test
    public void test() {

        Page<Demo> page = PageHelperUtils.startPage(1, 3);
        System.out.println("page查询前:" + page);
        List<Demo> datas = demoMapper.findAll();
        System.out.println("page查询后:" + page);
        System.out.println("数据结果:" + datas);
    }
}