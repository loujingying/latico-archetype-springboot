package com.latico.archetype.springboot.dao.secondary.jdbctemplate;

import com.latico.archetype.springboot.dao.secondary.entity.Demo2;

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
public interface Demo2JdbcTemplate {
    Demo2 queryById(String id);
    List<Demo2> queryAll();
}
