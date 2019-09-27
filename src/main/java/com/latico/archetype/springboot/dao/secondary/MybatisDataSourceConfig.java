package com.latico.archetype.springboot.dao.secondary;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.latico.archetype.springboot.config.DbConfig;
import com.latico.archetype.springboot.dao.primary.MybatisDataSourceConfigPrimary;
import com.latico.archetype.springboot.dao.primary.mapper.DemoMapper;
import com.latico.archetype.springboot.dao.secondary.mapper.Demo2Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
 * 把后缀带有secondary的名称全部替换目标字符串
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-02-26 09:25:32
 * @Version: 1.0
 */
@Configuration
@MapperScan(basePackageClasses = {Demo2Mapper.class},
        sqlSessionTemplateRef = MybatisDataSourceConfig.sqlSessionTemplateBeanName,
        sqlSessionFactoryRef = MybatisDataSourceConfig.sqlSessionFactoryBeanName)
public class MybatisDataSourceConfig {


    /**
     * TODO 需要修改的地方
     * mapper的xml文件的位置
     */
    private static final String MAPPER_LOCATION = "classpath*:mybatis/mapper/secondary/**/*.xml";


    /**
     * TODO
     */
    public static final String sqlSessionFactoryBeanName = "secondary.mybatis.sqlSessionFactory";

    /**
     * TODO
     */
    public static final String transactionManagerBeanName = "secondary.mybatis.transactionManager";
    /**
     * TODO
     */
    public static final String sqlSessionTemplateBeanName = "secondary.mybatis.sqlSessionTemplate";
    /**
     * mybatis配置
     */
    @Autowired
    private MybatisConfiguration mybatisConfiguration;

    /**
     * 数据源，拷贝后需要修改bean名称
     */
    @Resource(name = DbConfig.secondaryDatasourceConfigPrefix)
    private DataSource dataSource;


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

    @Bean(name = sqlSessionTemplateBeanName)
    public SqlSessionTemplate sqlSessionTemplateSecondary(@Qualifier(sqlSessionFactoryBeanName) SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}