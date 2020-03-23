package com.latico.archetype.springboot.elasticsearch.service.impl;

import com.latico.archetype.springboot.elasticsearch.entity.DemoElasticsearchBean;
import com.latico.archetype.springboot.elasticsearch.repository.DemoElasticsearchRepository;
import com.latico.archetype.springboot.elasticsearch.service.DemoElasticsearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * <PRE>
 *  
 * </PRE>
 * @author: latico
 * @date: 2020-03-23 11:42:41
 * @version: 1.0
 */
@Service
public class DemoElasticsearchServiceImpl implements DemoElasticsearchService {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Autowired
    private DemoElasticsearchRepository demoElasticsearchRepository;

    @Override
    public void createIndex() {
        elasticsearchRestTemplate.createIndex(DemoElasticsearchBean.class);
    }

    @Override
    public void deleteIndex(String index) {
        elasticsearchRestTemplate.deleteIndex(index);
    }

    @Override
    public void save(DemoElasticsearchBean demoElasticSearchBean) {
        demoElasticsearchRepository.save(demoElasticSearchBean);
    }

    @Override
    public void saveAll(List<DemoElasticsearchBean> list) {
        demoElasticsearchRepository.saveAll(list);
    }

    @Override
    public Iterator<DemoElasticsearchBean> findAll() {
        return demoElasticsearchRepository.findAll().iterator();
    }

    @Override
    public Page<DemoElasticsearchBean> findByContent(String content) {
        Pageable pageable = PageRequest.of(0, 10);
        return demoElasticsearchRepository.findByContent(content, pageable);
    }

    @Override
    public Page<DemoElasticsearchBean> findByFirstCode(String firstCode) {
        Pageable pageable = PageRequest.of(0, 10);
        return demoElasticsearchRepository.findByFirstCode(firstCode, pageable);
    }

    @Override
    public Page<DemoElasticsearchBean> findBySecordCode(String secordCode) {
        Pageable pageable = PageRequest.of(0, 10);
        return demoElasticsearchRepository.findBySecordCode(secordCode, pageable);
    }

    @Override
    public Page<DemoElasticsearchBean> query(String key) {
        Pageable pageable = PageRequest.of(0, 10);
        return demoElasticsearchRepository.findByContent(key, pageable);
    }
}

