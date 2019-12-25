package com.latico.archetype.springboot.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
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
     * TODO 可能需要修改的地方
     * 主数据库配置名称
     */
    public static final String dbConfigName_primary = "primary";

    /**
     * TODO 可能需要修改的地方
     * 第二个数据库配置名称
     */
    public static final String dbConfigName_secondary = "secondary";

    /**
     * 主数据源配置
     * 数据源配置前缀，跟application配置文件的要对应上
     */
    public static final String datasourceConfigPrefix_primary = "spring.datasource." + dbConfigName_primary;

    /**
     * 第二个数据源配置配置前缀，默认通过组拼字符串方式获取，如果不是，请修改
     */
    public static final String datasourceConfigPrefix_secondary = "spring.datasource." + dbConfigName_secondary;

    /**
     * 在这里，用了什么数据源就返回什么数据源，如果是返回{@link DataSource}，那么springboot2.0会默认使用HikariCP作为数据源
     * 拷贝后需要修改bean名称
     *
     * @return 主数据源
     */
    @Primary
    @Bean(name = datasourceConfigPrefix_primary)
    @ConfigurationProperties(prefix = datasourceConfigPrefix_primary)
    public DataSource primaryDatasource() {
        //默认方式，springboot2.0会默认使用HikariCP作为数据源
//        return DataSourceBuilder.create().build();

        // druid方式
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * @return 第二个数据源
     */
    @Bean(name = datasourceConfigPrefix_secondary)
    @ConfigurationProperties(prefix = datasourceConfigPrefix_secondary)
    public DataSource secondaryDatasource() {
        //默认方式，springboot2.0会默认使用HikariCP作为数据源
//        return DataSourceBuilder.create().build();

        // druid方式
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 因为mybatis的SqlSessionFactory是通过Configuration存放数据源，所以假如Configuration是单例的话，
     * 后面加载的数据源会把前面加载的覆盖掉，所以这里需要多例模式@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)，
     * com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean#buildSqlSessionFactory()里面
     * 调用了org.apache.ibatis.session.Configuration#setEnvironment(org.apache.ibatis.mapping.Environment)方法如下：
     * configuration.setEnvironment(new Environment(this.environment, this.transactionFactory, this.dataSource));
     * 看到这里，可以看到把当前数据源环境添加进去了覆盖掉之前的，所以假如Configuration是单例的话，后面加载的数据源会覆盖之前加载的，
     *
     * 还有一种方案：在手工创建MybatisSqlSessionFactoryBean的时候，
     * 每个都自己new一个新的MybatisConfiguration，而不是在这里使用配置文件的方式。
     * @return mybatis的配置
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public MybatisConfiguration mybatisConfiguration() {
        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        mybatisConfiguration.setJdbcTypeForNull(JdbcType.NULL);
        return mybatisConfiguration;
    }

}
