package com.latico.archetype.springboot.dao.secondary.jdbctemplate.impl;

import com.latico.archetype.springboot.dao.secondary.JdbcTemplateConfigSecondary;
import com.latico.archetype.springboot.dao.secondary.jdbctemplate.Demo2JdbcTemplate;
import com.latico.archetype.springboot.dao.secondary.entity.Demo2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
@Repository
public class Demo2JdbcTemplateImpl implements Demo2JdbcTemplate {
    @Autowired
    @Qualifier(JdbcTemplateConfigSecondary.jdbcTemplateBeanName)
    JdbcTemplate jdbcTemplate;

    RowMapper rowMapper = new Demo2RowMapper();

    @Override
    public Demo2 queryById(String id) {
        String sql = "select * from demo where id = ?";
        Object[] params = new Object[]{id};

        RowMapper rowMapper = getRowMapper();

        List<Demo2> objs = jdbcTemplate.query(sql ,params, rowMapper);

        if (objs != null && !objs.isEmpty()) {
            return objs.get(0);
        }
        return null;
    }

    @Override
    public List<Demo2> queryAll() {
        String sql = "select * from demo";

        RowMapper rowMapper = getRowMapper();

        return jdbcTemplate.query(sql, rowMapper);

    }

    private RowMapper getRowMapper() {
        return rowMapper;
    }

}

class Demo2RowMapper implements RowMapper<Demo2>{

    @Override
    public Demo2 mapRow(ResultSet rs, int rowNum) throws SQLException {
        Demo2 obj = new Demo2();
        obj.setAutoId(rs.getInt("auto_id"));
        obj.setId(rs.getString("id"));
        obj.setTaskId(rs.getInt("task_id"));
        obj.setExecType(rs.getString("exec_type"));

        return obj;
    }
}
