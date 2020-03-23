package com.latico.archetype.springboot.dao.secondary;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.latico.archetype.springboot.config.DbConfig;
import com.latico.archetype.springboot.dao.secondary.mapper.Demo2Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * <PRE>
 * Mybatis数据源
 * 这里配置成非主数据源，不能使用@Primary注解
 * <p>
 * 多数据源时使用，可以直接拷贝该类配置N个数据源
 * <p>
 * 数据源配置，如果不需要，可以把该类删掉，那么启动程序的时候就不会连接数据库
 * <p>
 * 如果拷贝该类创建一个新数据源，需要修改的地方有如下字段：
 * 把后缀带有primary的名称全部替换目标字符串
 * </PRE>
 *
 * @author: latico
 * @date: 2019-02-26 09:25:32
 * @version: 1.0
 */
@Configuration
@MapperScan(basePackageClasses = {Demo2Mapper.class},
        sqlSessionTemplateRef = MybatisDataSourceConfigSecondary.SQL_SESSION_TEMPLATE_BEAN_NAME)
public class MybatisDataSourceConfigSecondary {

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
     * mapper的xml文件的位置，如果不是按照这个默认模式目录结构，需要修改
     */
    private static final String MAPPER_LOCATION = "classpath*:mybatis/mapper/" + DB_CONFIG_NAME + "/**/*.xml";


    /**
     * SqlSessionFactory的bean名称
     */
    public static final String SQL_SESSION_FACTORY_BEAN_NAME = "mybatis.sqlSessionFactory." + DB_CONFIG_NAME;

    /**
     * 事务管理器bean名称
     */
    public static final String TRANSACTION_MANAGER_BEAN_NAME = "mybatis.transactionManager." + DB_CONFIG_NAME;
    /**
     * sqlSessionTemplate的bean名称
     */
    public static final String SQL_SESSION_TEMPLATE_BEAN_NAME = "mybatis.sqlSessionTemplate." + DB_CONFIG_NAME;
    /**
     * mybatis配置
     */
    @Autowired
    private MybatisConfiguration mybatisConfiguration;

    /**
     * 数据源，拷贝后需要修改bean名称
     */
    @Resource(name = DATA_SOURCE_BEAN_NAME)
    private DataSource dataSource;

    @Bean(name = SQL_SESSION_FACTORY_BEAN_NAME)
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //指定扫描的xml文件
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().
                getResources(MAPPER_LOCATION));
//        添加配置文件配置
        bean.setConfiguration(mybatisConfiguration);
        return bean.getObject();
    }

    @Bean(name = TRANSACTION_MANAGER_BEAN_NAME)
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = SQL_SESSION_TEMPLATE_BEAN_NAME)
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier(SQL_SESSION_FACTORY_BEAN_NAME) SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}