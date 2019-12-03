package com.latico.archetype.springboot.dao.primary;

import com.latico.archetype.springboot.config.DbConfig;
import org.mapstruct.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * <PRE>
 * JdbcTemplate第一个数据源，主数据源
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-12-03 15:35
 * @Version: 1.0
 */
@Configuration
public class JdbcTemplateConfigPrimary {

    /**
     * TODO 需要修改的地方
     * 数据库配置名称
     */
    public static final String dbConfigName = DbConfig.dbConfigName_primary;
    /**
     * TODO 需要修改的地方
     * 数据源bean名称
     */
    public static final String dataSourceBeanName = DbConfig.datasourceConfigPrefix_primary;

    /**
     * JdbcTemplate的bean名字
     */
    public static final String jdbcTemplateBeanName = "jdbcTemplate." + dbConfigName;

    /**
     * 数据源，拷贝后需要修改bean名称
     */
    @Resource(name = dataSourceBeanName)
    private DataSource dataSource;

    @Primary
    @Bean(name = jdbcTemplateBeanName)
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

}
