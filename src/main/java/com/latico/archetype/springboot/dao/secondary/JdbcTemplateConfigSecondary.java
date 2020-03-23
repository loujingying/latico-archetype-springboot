package com.latico.archetype.springboot.dao.secondary;

import com.latico.archetype.springboot.config.DbConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * <PRE>
 * JdbcTemplate第二个数据源
 * </PRE>
 *
 * @author: latico
 * @date: 2019-12-03 15:35
 * @version: 1.0
 */
@Configuration
public class JdbcTemplateConfigSecondary {

    /**
     * TODO 需要修改的地方
     * 数据库配置名称
     */
    public static final String DB_CONFIG_NAME = DbConfig.DB_CONFIG_NAME_SECONDARY;
    /**
     * TODO 需要修改的地方
     * 数据源bean名称
     */
    public static final String DATA_SOURCE_BEAN_NAME = DbConfig.DATASOURCE_CONFIG_PREFIX_SECONDARY;

    /**
     * JdbcTemplate的bean名字
     */
    public static final String JDBC_TEMPLATE_BEAN_NAME = "jdbcTemplate." + DB_CONFIG_NAME;

    /**
     * 数据源，拷贝后需要修改bean名称
     */
    @Resource(name = DATA_SOURCE_BEAN_NAME)
    private DataSource dataSource;

    @Bean(name = JDBC_TEMPLATE_BEAN_NAME)
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

}
