package com.latico.archetype.springboot.dao.primary.jdbctemplate;

import com.latico.archetype.springboot.dao.primary.entity.Demo;

import java.util.List;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @author: latico
 * @date: 2019-12-03 15:44
 * @version: 1.0
 */
public interface DemoJdbcTemplate {
    /**
     * 查询
     * @param id
     * @return
     */
    Demo queryById(String id);

    /**
     * 查询
     * @return
     */
    List<Demo> queryAll();
}
