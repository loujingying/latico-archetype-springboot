package com.latico.archetype.springboot.elasticsearch.service.impl;

import com.latico.archetype.springboot.elasticsearch.entity.DemoElasticSearchBean;
import com.latico.archetype.springboot.elasticsearch.repository.DemoElasticsearchRepository;
import com.latico.archetype.springboot.elasticsearch.service.DemoElasticSearchService;
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
public class DemoElasticSearchServiceImpl implements DemoElasticSearchService {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Autowired
    private DemoElasticsearchRepository demoElasticsearchRepository;

    @Override
    public void createIndex() {
        elasticsearchRestTemplate.createIndex(DemoElasticSearchBean.class);
    }

    @Override
    public void deleteIndex(String index) {
        elasticsearchRestTemplate.deleteIndex(index);
    }

    @Override
    public void save(DemoElasticSearchBean DemoElasticSearchBean) {
        demoElasticsearchRepository.save(DemoElasticSearchBean);
    }

    @Override
    public void saveAll(List<DemoElasticSearchBean> list) {
        demoElasticsearchRepository.saveAll(list);
    }

    @Override
    public Iterator<DemoElasticSearchBean> findAll() {
        return demoElasticsearchRepository.findAll().iterator();
    }

    @Override
    public Page<DemoElasticSearchBean> findByContent(String content) {
        Pageable pageable = PageRequest.of(0, 10);
        return demoElasticsearchRepository.findByContent(content, pageable);
    }

    @Override
    public Page<DemoElasticSearchBean> findByFirstCode(String firstCode) {
        Pageable pageable = PageRequest.of(0, 10);
        return demoElasticsearchRepository.findByFirstCode(firstCode, pageable);
    }

    @Override
    public Page<DemoElasticSearchBean> findBySecordCode(String secordCode) {
        Pageable pageable = PageRequest.of(0, 10);
        return demoElasticsearchRepository.findBySecordCode(secordCode, pageable);
    }

    @Override
    public Page<DemoElasticSearchBean> query(String key) {
        Pageable pageable = PageRequest.of(0, 10);
        return demoElasticsearchRepository.findByContent(key, pageable);
    }
}

