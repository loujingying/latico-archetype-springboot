package com.latico.archetype.springboot.dao.primary.jdbctemplate;

import com.latico.archetype.springboot.dao.primary.entity.Demo;

import java.util.List;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-12-03 15:44
 * @Version: 1.0
 */
public interface DemoJdbcTemplate {
    Demo queryById(String id);
    List<Demo> queryAll();
}
