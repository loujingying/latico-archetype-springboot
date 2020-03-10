package com.latico.archetype.springboot.dao.elasticsearch.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class DemoElasticsearchBeanTest {

    /**
     *
     */
    @Test
    public void test(){
        DemoElasticsearchBean demoElasticsearchBean = new DemoElasticsearchBean();
        demoElasticsearchBean.setContent("ajjg");
    }
}