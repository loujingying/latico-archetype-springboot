package com.latico.archetype.springboot.postgresql.service;


import com.latico.archetype.springboot.postgresql.entity.PgDemo;

import java.util.List;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @author: latico
 * @date: 2020-04-08 14:30
 * @version: 1.0
 */
public interface DemoPostgreSqlServicee {

    void init();

    List<PgDemo> queryAll();

    String deleteAll();

    List<PgDemo> queryByPage();

}
