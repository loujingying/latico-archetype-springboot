package com.latico.archetype.springboot.mongodb.service;

import com.latico.archetype.springboot.common.util.PageableUtils;
import com.latico.archetype.springboot.mongodb.entity.DemoMongoDbBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @author: latico
 * @date: 2020-03-26 15:52
 * @version: 1.0
 */
public interface DemoMongoDbService {
    /**
     * 初始化一些数据进数据库中
     */
    public void init();

    /**
     * 查询所有
     * @return
     */
    public List<DemoMongoDbBean> queryAll();

    /**
     * 删除所有
     * @return
     */
    public String deleteAll();


    /**
     * 通过分页查询
     * @return
     */
    public List<DemoMongoDbBean> queryByPage();
}
