package com.latico.archetype.springboot.postgresql.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @author: latico
 * @date: 2020-04-08 14:37
 * @version: 1.0
 */
@Configuration
public class PostgreSqlConfig {
    /**
     * TODO 可能需要修改的地方
     * 主数据库配置名称
     */
    public static final String DB_CONFIG_NAME_PRIMARY = "postgresql";


    /**
     * 主数据源配置
     * 数据源配置前缀，跟application配置文件的要对应上
     */
    public static final String DATASOURCE_CONFIG_PREFIX_PRIMARY = "spring.datasource." + DB_CONFIG_NAME_PRIMARY;

    /**
     * 在这里，用了什么数据源就返回什么数据源，如果是返回{@link DataSource}，那么springboot2.0会默认使用HikariCP作为数据源
     * 拷贝后需要修改bean名称
     *
     * @return 主数据源
     */
    @Bean(name = DATASOURCE_CONFIG_PREFIX_PRIMARY)
    @ConfigurationProperties(prefix = DATASOURCE_CONFIG_PREFIX_PRIMARY)
    public DataSource postgreSqldatasource() {
        // druid方式
        return DruidDataSourceBuilder.create().build();
    }
}
