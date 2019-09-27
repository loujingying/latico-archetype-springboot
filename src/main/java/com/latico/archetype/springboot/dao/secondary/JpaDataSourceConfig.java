package com.latico.archetype.springboot.dao.secondary;

import com.latico.archetype.springboot.common.jpa.BaseRepositoryFactoryBean;
import com.latico.archetype.springboot.config.DbConfig;
import com.latico.archetype.springboot.dao.primary.entity.Demo;
import com.latico.archetype.springboot.dao.secondary.entity.Demo2;
import com.latico.archetype.springboot.dao.secondary.repository.Demo2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

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
 * 把后缀带有secondary的名称全部替换目标字符串
 *
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-02-26 09:24:06
 * @Version: 1.0
 */
@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = JpaDataSourceConfig.entityManagerFactoryBeanName,
        transactionManagerRef = JpaDataSourceConfig.transactionManagerBeanName,
        basePackageClasses = {Demo2Repository.class},
        repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class JpaDataSourceConfig {


    /**
     * TODO
     * 实体目录
     * 拷贝后需要修改具体的实体包路径
     */
    public static final String ENTITY_PREFIX = Demo2.class.getPackage().getName();

    /**
     *TODO
     */
    public static final String entityManagerBeanName = "secondary.jpa.entityManager";
    /**
     *TODO
     */
    public static final String entityManagerFactoryBuilderBeanName = "secondary.jpa.entityManagerFactoryBuilder";
    /**
     *TODO
     */
    public static final String entityManagerFactoryBeanName = "secondary.jpa.entityManagerFactory";
    /**
     *TODO
     */
    public static final String persistenceUnitBeanName = "secondary.jpa.persistenceUnit";
    /**
     *TODO
     */
    public static final String transactionManagerBeanName = "secondary.jpa.transactionManager";

    /**
     * JPA配置
     */
    @Autowired(required = false)
    private JpaProperties jpaProperties;

    /**
     * 数据源，拷贝后需要修改bean名称
     */
    @Resource(name = DbConfig.secondaryDatasourceConfigPrefix)
    private DataSource dataSource;


    /**
     * 持久化管理器
     */
    @Autowired(required = false)
    private PersistenceUnitManager persistenceUnitManager;

    /**
     * @return 配置信息
     */
    private Map<String, String> getJpaProperties() {
        if (jpaProperties == null) {
            return new HashMap<>();
        }
        return jpaProperties.getProperties();
    }

    @Bean(name = entityManagerBeanName)
    public EntityManager entityManager(@Qualifier(entityManagerFactoryBuilderBeanName) EntityManagerFactoryBuilder builder) {
        return entityManagerFactory(builder).getObject().createEntityManager();
    }

    @Bean(name = entityManagerFactoryBuilderBeanName)
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
//        创建hibernate适配器
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        return new EntityManagerFactoryBuilder(adapter, getJpaProperties(), persistenceUnitManager);
    }

    /**
     * 设置实体类所在位置
     */
    @Bean(name = entityManagerFactoryBeanName)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier(entityManagerFactoryBuilderBeanName) EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages(ENTITY_PREFIX)
                .persistenceUnit(persistenceUnitBeanName)
                .properties(getJpaProperties())
                .build();
    }

    @Bean(name = transactionManagerBeanName)
    public PlatformTransactionManager transactionManager(@Qualifier(entityManagerFactoryBuilderBeanName) EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactory(builder).getObject());
    }

}