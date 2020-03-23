package com.latico.archetype.springboot.dao.secondary.jdbctemplate.impl;

import com.latico.archetype.springboot.dao.secondary.JdbcTemplateConfigSecondary;
import com.latico.archetype.springboot.dao.secondary.entity.Demo2;
import com.latico.archetype.springboot.dao.secondary.jdbctemplate.Demo2JdbcTemplate;
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
public class Demo2JdbcTemplateImpl implements Demo2JdbcTemplate {
    /**
     * 注入备的数据源模板
     */
    @Autowired
    @Qualifier(JdbcTemplateConfigSecondary.JDBC_TEMPLATE_BEAN_NAME)
    private JdbcTemplate jdbcTemplate;

    /**
     * 这里为了复用
     */
    private RowMapper<Demo2> rowMapper = makeRowMapper();

    /**
     * @return 获取一个行记录对象映射
     */
    private RowMapper<Demo2> getRowMapper() {
        return rowMapper;
    }

    /**
     * 制造一个行记录对象映射
     *
     * @return
     */
    private RowMapper<Demo2> makeRowMapper() {
        RowMapper<Demo2> rowMapper = new RowMapper<Demo2>() {

            @Override
            public Demo2 mapRow(ResultSet rs, int rowNum) throws SQLException {
                Demo2 obj = new Demo2();
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
    public Demo2 queryById(String id) {
        String sql = "select * from demo where id = ?";
        Object[] params = new Object[]{id};

        RowMapper rowMapper = getRowMapper();

        List<Demo2> objs = jdbcTemplate.query(sql, params, rowMapper);

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

}

