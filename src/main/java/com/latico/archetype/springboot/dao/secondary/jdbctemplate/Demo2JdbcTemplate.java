package com.latico.archetype.springboot.dao.secondary.jdbctemplate;

import com.latico.archetype.springboot.dao.secondary.entity.Demo2;

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
public interface Demo2JdbcTemplate {
    /**
     * 查询
     * @param id
     * @return
     */
    Demo2 queryById(String id);

    /**
     * 查询
     * @return
     */
    List<Demo2> queryAll();
}
