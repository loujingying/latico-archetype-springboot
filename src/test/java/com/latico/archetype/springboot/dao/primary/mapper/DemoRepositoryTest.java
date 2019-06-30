package com.latico.archetype.springboot.dao.primary.mapper;

import com.latico.archetype.springboot.common.util.PageableUtils;
import com.latico.archetype.springboot.dao.DaoTestApplication;
import com.latico.archetype.springboot.dao.primary.entity.Demo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DaoTestApplication.class)
public class DemoRepositoryTest {
    @Autowired
    DemoRepository demoRepository;

    @Test
    public void test() {
        int pageNum = 1;
        int perPageSize = 3;
        String sortField = "id";

        Pageable pageable = PageableUtils.createPageable(pageNum, perPageSize, Sort.Direction.ASC, sortField);
        Page<Demo> page = demoRepository.findAll(pageable);
        System.out.println("页总数：" + page.getTotalPages());
        System.out.println("数量总数:" + page.getTotalElements());
        System.out.println("数据结果："+page.getContent());
    }

}