package com.latico.archetype.springboot.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * <PRE>
 * 数据库配置
 * 数据源配置
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-09-20 15:22
 * @Version: 1.0
 */
@EnableTransactionManagement
@Configuration
public class DbConfig {

    /**
     * 主数据源配置
     * 数据源配置前缀，跟application配置文件的要对应上
     */
    public static final String primaryDatasourceConfigPrefix = "spring.datasource.primary";

    /**
     * 第二个数据源配置
     */
    public static final String secondaryDatasourceConfigPrefix = "spring.datasource.secondary";

    /**
     * 在这里，用了什么数据源就返回什么数据源，如果是返回{@link DataSource}，那么springboot2.0会默认使用HikariCP作为数据源
     * 拷贝后需要修改bean名称
     *
     * @return
     */
    @Primary
    @Bean(name = primaryDatasourceConfigPrefix)
    @ConfigurationProperties(prefix = primaryDatasourceConfigPrefix)
    public DataSource primaryDatasource() {
        //默认方式，springboot2.0会默认使用HikariCP作为数据源
//        return DataSourceBuilder.create().build();

        // druid方式
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = secondaryDatasourceConfigPrefix)
    @ConfigurationProperties(prefix = secondaryDatasourceConfigPrefix)
    public DataSource secondaryDatasource() {
        //默认方式，springboot2.0会默认使用HikariCP作为数据源
//        return DataSourceBuilder.create().build();

        // druid方式
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * @return mybatis的配置
     */
    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public MybatisConfiguration mybatisConfiguration() {
        return new MybatisConfiguration();
    }

}
