package com.latico.archetype.springboot.dao.primary.mapper;

import org.junit.Test;

import static org.junit.Assert.*;

public class DemoDaoRepositoryTest {

    /**
     *
     */
    @Test
    public void test(){
        DemoDaoRepository demo = new DemoDaoRepository();
        System.out.println(demo.genricClass);
    }
}