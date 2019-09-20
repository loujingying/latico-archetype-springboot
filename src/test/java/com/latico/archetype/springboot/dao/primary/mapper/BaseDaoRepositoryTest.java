package com.latico.archetype.springboot.dao.primary.mapper;

import com.latico.archetype.springboot.common.dao.AbstractDao;
import com.latico.archetype.springboot.dao.primary.entity.Demo;
import org.junit.Test;

public class BaseDaoRepositoryTest {

    /**
     *
     */
    @Test
    public void test(){
        AbstractDao<Demo, Integer> dao = new AbstractDao<>();
        System.out.println(dao.genricClass);
    }
}