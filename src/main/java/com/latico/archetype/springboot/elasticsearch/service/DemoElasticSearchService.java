package com.latico.archetype.springboot.elasticsearch.service;

import com.latico.archetype.springboot.elasticsearch.entity.DemoElasticSearchBean;
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
public interface DemoElasticSearchService {

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
     * @param DemoElasticSearchBean
     */
    void save(DemoElasticSearchBean DemoElasticSearchBean);

    /**
     * 保存所有
     * @param list
     */
    void saveAll(List<DemoElasticSearchBean> list);

    /**
     * 查询所有
     * @return
     */
    Iterator<DemoElasticSearchBean> findAll();

    /**
     * 查询
     * @param content
     * @return
     */
    Page<DemoElasticSearchBean> findByContent(String content);

    /**
     * 查询
     * @param firstCode
     * @return
     */
    Page<DemoElasticSearchBean> findByFirstCode(String firstCode);

    /**
     * 查询
     * @param secordCode
     * @return
     */
    Page<DemoElasticSearchBean> findBySecordCode(String secordCode);

    /**
     * 查询
     * @param key
     * @return
     */
    Page<DemoElasticSearchBean> query(String key);
}

