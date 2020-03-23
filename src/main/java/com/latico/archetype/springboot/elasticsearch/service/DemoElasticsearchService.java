package com.latico.archetype.springboot.elasticsearch.service;

import com.latico.archetype.springboot.elasticsearch.entity.DemoElasticsearchBean;
import org.springframework.data.domain.Page;

import java.util.Iterator;
import java.util.List;

/**
 * <PRE>
 *  演示elasticsearch业务处理
 * </PRE>
 * @author: latico
 * @date: 2020-03-10 11:51:27
 * @version: 1.0
 */
public interface DemoElasticsearchService {

    /**
     * 创建索引
     */
    void createIndex();

    /**
     * 删除索引
     * @param index
     */
    void deleteIndex(String index);

    /**
     * 保存记录
     * @param demoElasticSearchBean
     */
    void save(DemoElasticsearchBean demoElasticSearchBean);

    /**
     * 保存所有
     * @param list
     */
    void saveAll(List<DemoElasticsearchBean> list);

    /**
     * 查询所有
     * @return
     */
    Iterator<DemoElasticsearchBean> findAll();

    /**
     * 查询
     * @param content
     * @return
     */
    Page<DemoElasticsearchBean> findByContent(String content);

    /**
     * 查询
     * @param firstCode
     * @return
     */
    Page<DemoElasticsearchBean> findByFirstCode(String firstCode);

    /**
     * 查询
     * @param secordCode
     * @return
     */
    Page<DemoElasticsearchBean> findBySecordCode(String secordCode);

    /**
     * 查询
     * @param key
     * @return
     */
    Page<DemoElasticsearchBean> query(String key);
}

