package com.latico.archetype.springboot.dao.primary;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * <PRE>
 * spring data JPA方式的数据源
 * 默认数据源配置，也就是主数据源，需要使用@Primary注解,一个程序只能有一个主数据源，非主数据源，不能有@Primary注解
 * 多数据源时使用，可以直接拷贝该类配置N个数据源，但是注意拷贝该类带有@Primary注解需要手工删除。
 * 数据源配置，如果不需要，可以把该类删掉，那么启动程序的时候就不会连接数据库
 * <p>
 * 如果拷贝该类创建一个新数据源，需要修改的地方有如下字段：
 * 把后缀带有primary的名称全部替换目标字符串
 *
 * </PRE>
 *
 * @Author: LanDingDong
 * @Date: 2019-02-26 09:24:06
 * @Version: 1.0
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = JpaDataSourceConfigPrimary.entityManagerFactoryBeanName,
        transactionManagerRef = JpaDataSourceConfigPrimary.transactionManagerBeanName,
        basePackages = {JpaDataSourceConfigPrimary.MAPPER_PACKAGE})
public class JpaDataSourceConfigPrimary {

    /**
     * TODO
     * 数据源配置前缀，跟application配置文件的要对应上
     */
    public static final String DATASOURCE_CONFIG_PREFIX = "spring.datasource.primary";

    /**
     * TODO
     * mapper的接口类
     * 精确到 master 目录，以便跟其他数据源隔离
     */
    public static final String MAPPER_PACKAGE = "com.latico.archetype.springboot.dao.primary.mapper";

    /**
     * TODO
     * 实体目录
     * 拷贝后需要修改具体的实体包路径
     */
    public static final String ENTITY_PREFIX = "com.latico.archetype.springboot.dao.primary.entity";
    /**
     * TODO
     * 数据源名称,因为外界可能要拿到数据源名称，所以public
     */
    public static final String dataSourceBeanName = "com.latico.archetype.springboot.dao.primary.dataSource";

    /**
     *
     */
    public static final String entityManagerBeanName = "com.latico.archetype.springboot.dao.primary.entityManager";
    /**
     *
     */
    public static final String entityManagerFactoryBuilderBeanName = "com.latico.archetype.springboot.dao.primary.entityManagerFactoryBuilder";
    /**
     *
     */
    public static final String entityManagerFactoryBeanName = "com.latico.archetype.springboot.dao.primary.entityManagerFactory";
    /**
     *
     */
    public static final String persistenceUnitBeanName = "com.latico.archetype.springboot.dao.primary.persistenceUnit";
    /**
     *
     */
    public static final String transactionManagerBeanName = "com.latico.archetype.springboot.dao.primary.transactionManager";

    /**
     * JPA配置
     */
    @Autowired(required = false)
    private JpaProperties jpaProperties;

    /**
     * 数据源，拷贝后需要修改bean名称
     */
    @Resource(name = dataSourceBeanName)
    private DataSource dataSource;


    /**
     * 持久化管理器
     */
    @Autowired(required = false)
    private PersistenceUnitManager persistenceUnitManager;

    /**
     * 在这里，用了什么数据源就返回什么数据源，如果是返回{@link DataSource}，那么springboot2.0会默认使用HikariCP作为数据源
     * 拷贝后需要修改bean名称
     *
     * @return
     */
    @Primary
    @Bean(name = dataSourceBeanName)
    @ConfigurationProperties(prefix = DATASOURCE_CONFIG_PREFIX)
    public DataSource dataSource() {
        //默认方式，springboot2.0会默认使用HikariCP作为数据源
//        return DataSourceBuilder.create().build();

        // druid方式
        return DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = entityManagerBeanName)
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactory(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = entityManagerFactoryBuilderBeanName)
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
//        创建hibernate适配器
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        return new EntityManagerFactoryBuilder(adapter, getVendorProperties(), persistenceUnitManager);
    }

    /**
     * @return 配置信息
     */
    private Map<String, String> getVendorProperties() {
        if (jpaProperties == null) {
            return new HashMap<>();
        }
        return jpaProperties.getProperties();
    }

    /**
     * 设置实体类所在位置
     */
    @Primary
    @Bean(name = entityManagerFactoryBeanName)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages(ENTITY_PREFIX)
                .persistenceUnit(persistenceUnitBeanName)
                .properties(getVendorProperties())
                .build();
    }

    @Primary
    @Bean(name = transactionManagerBeanName)
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactory(builder).getObject());
    }

}