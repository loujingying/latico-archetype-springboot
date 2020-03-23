package com.latico.archetype.springboot.dao.primary.jdbctemplate.impl;

import com.latico.archetype.springboot.dao.primary.JdbcTemplateConfigPrimary;
import com.latico.archetype.springboot.dao.primary.entity.Demo;
import com.latico.archetype.springboot.dao.primary.jdbctemplate.DemoJdbcTemplate;
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
 * @author: latico
 * @date: 2019-12-03 15:44
 * @version: 1.0
 */
@Repository
public class DemoJdbcTemplateImpl implements DemoJdbcTemplate {
    /**
     * 注入主数据源的模板
     */
    @Autowired
    @Qualifier(JdbcTemplateConfigPrimary.JDBC_TEMPLATE_BEAN_NAME)
    private JdbcTemplate jdbcTemplate;

    /**
     * 这里为了复用
     */
    private RowMapper demoRowMapper = makeRowMapper();

    /**
     * 获取一个行记录对象映射
     *
     * @return
     */
    private RowMapper getRowMapper() {
        return demoRowMapper;
    }

    /**
     * 制造一个行记录对象映射
     *
     * @return
     */
    private RowMapper<Demo> makeRowMapper() {
        RowMapper<Demo> rowMapper = new RowMapper<Demo>() {

            @Override
            public Demo mapRow(ResultSet rs, int rowNum) throws SQLException {
                Demo obj = new Demo();
                obj.setAutoId(rs.getInt("auto_id"));
                obj.setId(rs.getString("id"));
                obj.setTaskId(rs.getInt("task_id"));
                obj.setExecType(rs.getString("exec_type"));

                return obj;
            }
        };
        return rowMapper;
    }

    @Override
    public Demo queryById(String id) {
        String sql = "select * from demo where id = ?";
        Object[] params = new Object[]{id};

        RowMapper rowMapper = getRowMapper();

        List<Demo> objs = jdbcTemplate.query(sql, params, rowMapper);

        if (objs != null && !objs.isEmpty()) {
            return objs.get(0);
        }
        return null;
    }


    @Override
    public List<Demo> queryAll() {
        String sql = "select * from demo";

        RowMapper rowMapper = getRowMapper();

        return jdbcTemplate.query(sql, rowMapper);

    }

}

