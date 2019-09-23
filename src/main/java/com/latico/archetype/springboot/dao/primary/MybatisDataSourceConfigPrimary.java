package com.latico.archetype.springboot.dao.primary;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.latico.archetype.springboot.config.DbConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
@MapperScan(basePackages = MybatisDataSourceConfigPrimary.MAPPER_PACKAGE,
        sqlSessionTemplateRef = MybatisDataSourceConfigPrimary.sqlSessionTemplateBeanName)
public class MybatisDataSourceConfigPrimary {

    /**
     * TODO 需要修改的地方
     * mapper的接口类
     * 精确到 master 目录，以便跟其他数据源隔离
     */
    public static final String MAPPER_PACKAGE = "com.latico.archetype.springboot.dao.primary.mapper";

    /**
     * TODO 需要修改的地方
     * mapper的xml文件的位置
     */
    private static final String MAPPER_LOCATION = "classpath*:mybatis/mapper/primary/**/*.xml";


    /**
     * TODO
     */
    public static final String sqlSessionFactoryBeanName = "com.latico.archetype.springboot.dao.primary.sqlSessionFactory";

    /**
     * TODO
     */
    public static final String transactionManagerBeanName = "com.latico.archetype.springboot.dao.primary.mybatisTransactionManager";
    /**
     * TODO
     */
    public static final String sqlSessionTemplateBeanName = "com.latico.archetype.springboot.dao.primary.sqlSessionTemplate";
    /**
     * mybatis配置
     */
    @Autowired
    private MybatisConfiguration mybatisConfiguration;

    /**
     * 数据源，拷贝后需要修改bean名称
     */
    @Resource(name = DbConfig.primaryDatasourceConfigPrefix)
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