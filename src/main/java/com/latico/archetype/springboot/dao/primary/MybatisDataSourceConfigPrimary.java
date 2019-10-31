package com.latico.archetype.springboot.dao.primary;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.latico.archetype.springboot.config.DbConfig;
import com.latico.archetype.springboot.dao.primary.mapper.DemoMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
 * @Author: latico
 * @Date: 2019-02-26 09:25:32
 * @Version: 1.0
 */
@Configuration
@MapperScan(basePackageClasses = {DemoMapper.class},
        sqlSessionTemplateRef = MybatisDataSourceConfigPrimary.sqlSessionTemplateBeanName,
        sqlSessionFactoryRef = MybatisDataSourceConfigPrimary.sqlSessionFactoryBeanName)
public class MybatisDataSourceConfigPrimary {

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
     * mapper的xml文件的位置，如果不是按照这个默认模式目录结构，需要修改
     */
    private static final String MAPPER_LOCATION = "classpath*:mybatis/mapper/" + dbConfigName + "/**/*.xml";


    /**
     * SqlSessionFactory的bean名称
     */
    public static final String sqlSessionFactoryBeanName = "mybatis.sqlSessionFactory." + dbConfigName;

    /**
     * 事务管理器bean名称
     */
    public static final String transactionManagerBeanName = "mybatis.transactionManager." + dbConfigName;
    /**
     * sqlSessionTemplate的bean名称
     */
    public static final String sqlSessionTemplateBeanName = "mybatis.sqlSessionTemplate." + dbConfigName;
    /**
     * mybatis配置
     */
    @Autowired
    private MybatisConfiguration mybatisConfiguration;

    /**
     * 数据源，拷贝后需要修改bean名称
     */
    @Resource(name = dataSourceBeanName)
    private DataSource dataSource;

    @Primary
    @Bean(name = sqlSessionFactoryBeanName)
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

    @Bean(name = transactionManagerBeanName)
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = sqlSessionTemplateBeanName)
    public SqlSessionTemplate sqlSessionTemplateSecondary(@Qualifier(sqlSessionFactoryBeanName) SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}