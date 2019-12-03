package com.latico.archetype.springboot.dao.primary.jdbctemplate.impl;

import com.latico.archetype.springboot.dao.primary.JdbcTemplateConfigPrimary;
import com.latico.archetype.springboot.dao.primary.jdbctemplate.DemoJdbcTemplate;
import com.latico.archetype.springboot.dao.primary.entity.Demo;
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
public class DemoJdbcTemplateImpl implements DemoJdbcTemplate {
    @Autowired
    @Qualifier(JdbcTemplateConfigPrimary.jdbcTemplateBeanName)
    JdbcTemplate jdbcTemplate;

    RowMapper demoRowMapper = new DemoRowMapper();

    @Override
    public Demo queryById(String id) {
        String sql = "select * from demo where id = ?";
        Object[] params = new Object[]{id};

        RowMapper rowMapper = getRowMapper();

        List<Demo> objs = jdbcTemplate.query(sql ,params, rowMapper);

        if (objs != null && !objs.isEmpty()) {
            return objs.get(0);
        }
        return null;
    }

    private RowMapper getRowMapper() {
        return demoRowMapper;
    }

    @Override
    public List<Demo> queryAll() {
        String sql = "select * from demo";

        RowMapper rowMapper = getRowMapper();

        return jdbcTemplate.query(sql, rowMapper);

    }


}

class DemoRowMapper implements RowMapper<Demo>{

    @Override
    public Demo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Demo obj = new Demo();
        obj.setAutoId(rs.getInt("auto_id"));
        obj.setId(rs.getString("id"));
        obj.setTaskId(rs.getInt("task_id"));
        obj.setExecType(rs.getString("exec_type"));

        return obj;
    }
}
