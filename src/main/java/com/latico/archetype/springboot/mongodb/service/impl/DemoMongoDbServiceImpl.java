package com.latico.archetype.springboot.mongodb.service.impl;

import com.latico.archetype.springboot.common.util.PageableUtils;
import com.latico.archetype.springboot.mongodb.entity.DemoMongoDbBean;
import com.latico.archetype.springboot.mongodb.repository.DemoMongoDbBeanRepository;
import com.latico.archetype.springboot.mongodb.service.DemoMongoDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @author: latico
 * @date: 2020-03-26 15:53
 * @version: 1.0
 */
@Service
public class DemoMongoDbServiceImpl implements DemoMongoDbService {

    @Autowired
    private DemoMongoDbBeanRepository demoMongoDbBeanRepository;

    @Override
    public void init(){
        demoMongoDbBeanRepository.save(new DemoMongoDbBean(1L, "aaaaa", 10));
        demoMongoDbBeanRepository.save(new DemoMongoDbBean(2L, "bbbaa", 20));
        demoMongoDbBeanRepository.save(new DemoMongoDbBean(3L, "cccaa", 30));
        demoMongoDbBeanRepository.save(new DemoMongoDbBean(4L, "dddaa", 40));
        demoMongoDbBeanRepository.save(new DemoMongoDbBean(5L, "eeeaa", 50));
        demoMongoDbBeanRepository.save(new DemoMongoDbBean(6L, "fffee", 60));
        demoMongoDbBeanRepository.save(new DemoMongoDbBean(7L, "gggee", 70));
        demoMongoDbBeanRepository.save(new DemoMongoDbBean(8L, "hhhee", 80));
        demoMongoDbBeanRepository.save(new DemoMongoDbBean(9L, "iiiee", 90));

    }

    @Override
    public List<DemoMongoDbBean> queryAll(){
        return demoMongoDbBeanRepository.findAll();
    }

    @Override
    public String deleteAll(){
        demoMongoDbBeanRepository.deleteAll();
        return "删除所有成功";
    }


    @Override
    public List<DemoMongoDbBean> queryByPage(){
        Pageable pageable = PageableUtils.createPageable(1, 5, Sort.Direction.ASC, "id");
        Page<DemoMongoDbBean> page = demoMongoDbBeanRepository.findAll(pageable);
        return page.getContent();
    }

}
