package com.latico.archetype.springboot.service;

import com.latico.archetype.springboot.dao.elasticsearch.entity.DemoElasticsearchBean;
import org.springframework.data.domain.Page;

import java.util.Iterator;
import java.util.List;

/**
 * <PRE>
 *  演示elasticsearch业务处理
 * </PRE>
 * @Author: latico
 * @Date: 2020-03-10 11:51:27
 * @Version: 1.0
 */
public interface DemoElasticsearchService {

    void createIndex();

    void deleteIndex(String index);

    void save(DemoElasticsearchBean DemoElasticsearchBean);

    void saveAll(List<DemoElasticsearchBean> list);

    Iterator<DemoElasticsearchBean> findAll();

    Page<DemoElasticsearchBean> findByContent(String content);

    Page<DemoElasticsearchBean> findByFirstCode(String firstCode);

    Page<DemoElasticsearchBean> findBySecordCode(String secordCode);

    Page<DemoElasticsearchBean> query(String key);
}

