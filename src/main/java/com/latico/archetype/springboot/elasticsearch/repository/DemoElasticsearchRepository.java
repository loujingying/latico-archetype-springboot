package com.latico.archetype.springboot.elasticsearch.repository;

import com.latico.archetype.springboot.elasticsearch.entity.DemoElasticSearchBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @author: latico
 * @date: 2020-03-10 10:48
 * @version: 1.0
 */
@Repository
public interface DemoElasticsearchRepository extends ElasticsearchRepository<DemoElasticSearchBean, String> {
    /**
     * 查找
     * @param content
     * @param pageable
     * @return
     */
    //@Query("{\"bool\" : {\"must\" : {\"field\" : {\"content\" : \"?\"}}}}")
    Page<DemoElasticSearchBean> findByContent(String content, Pageable pageable);

    /**
     * 查询
     * @param firstCode
     * @param pageable
     * @return
     */
    @Query("{\"bool\" : {\"must\" : {\"field\" : {\"firstCode.keyword\" : \"?\"}}}}")
    Page<DemoElasticSearchBean> findByFirstCode(String firstCode, Pageable pageable);

    /**
     * 查询
     * @param secordCode
     * @param pageable
     * @return
     */
    @Query("{\"bool\" : {\"must\" : {\"field\" : {\"secordCode.keyword\" : \"?\"}}}}")
    Page<DemoElasticSearchBean> findBySecordCode(String secordCode, Pageable pageable);
}
