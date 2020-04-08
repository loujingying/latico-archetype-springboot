package com.latico.archetype.springboot.postgresql.config;

import com.latico.archetype.springboot.common.jpa.BaseRepositoryFactoryBean;
import com.latico.archetype.springboot.postgresql.entity.PgDemo;
import com.latico.archetype.springboot.postgresql.repository.PgDemoRepository;
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
 * 把后缀带有primary的名称全部替换目标字符串
 *
 * </PRE>
 *
 * @author: latico
 * @date: 2019-02-26 09:24:06
 * @version: 1.0
 */
@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = PostgreSqlJpaDataSourceConfig.ENTITY_MANAGER_FACTORY_BEAN_NAME,
        transactionManagerRef = PostgreSqlJpaDataSourceConfig.TRANSACTION_MANAGER_BEAN_NAME,
        basePackageClasses = {PgDemoRepository.class},
        repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class PostgreSqlJpaDataSourceConfig {

    /**
     * TODO 需要修改的地方
     * 数据库配置名称
     */
    public static final String DB_CONFIG_NAME = PostgreSqlConfig.DB_CONFIG_NAME_PRIMARY;
    /**
     * TODO 需要修改的地方
     * 数据源bean名称
     */
    public static final String DATA_SOURCE_BEAN_NAME = PostgreSqlConfig.DATASOURCE_CONFIG_PREFIX_PRIMARY;
    /**
     * TODO 需要修改的地方
     * 实体目录
     * 拷贝后需要修改具体的实体包路径
     */
    public static final String ENTITY_PREFIX = PgDemo.class.getPackage().getName();

    /**
     * 实体管理器bean名称
     */
    public static final String ENTITY_MANAGER_BEAN_NAME = "jpa.entityManager." + DB_CONFIG_NAME;
    /**
     * 实体管理器工厂建造器bean名称
     */
    public static final String ENTITY_MANAGER_FACTORY_BUILDER_BEAN_NAME = "jpa.entityManagerFactoryBuilder." + DB_CONFIG_NAME;
    /**
     * 实体管理器工厂bean名称
     */
    public static final String ENTITY_MANAGER_FACTORY_BEAN_NAME = "jpa.entityManagerFactory." + DB_CONFIG_NAME;
    /**
     * 持久化单元bean名称
     */
    public static final String PERSISTENCE_UNIT_BEAN_NAME = "jpa.persistenceUnit." + DB_CONFIG_NAME;
    /**
     * 事务管理器bean名称
     */
    public static final String TRANSACTION_MANAGER_BEAN_NAME = "jpa.transactionManager." + DB_CONFIG_NAME;

    /**
     * JPA配置
     */
    @Autowired(required = false)
    private JpaProperties jpaProperties;

    /**
     * 数据源，拷贝后需要修改bean名称
     */
    @Resource(name = DATA_SOURCE_BEAN_NAME)
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
            return new HashMap<>(16);
        }
        return jpaProperties.getProperties();
    }

    @Bean(name = ENTITY_MANAGER_BEAN_NAME)
    public EntityManager entityManager(@Qualifier(ENTITY_MANAGER_FACTORY_BUILDER_BEAN_NAME) EntityManagerFactoryBuilder builder) {
        return entityManagerFactory(builder).getObject().createEntityManager();
    }

    @Bean(name = ENTITY_MANAGER_FACTORY_BUILDER_BEAN_NAME)
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
//        创建hibernate适配器
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        return new EntityManagerFactoryBuilder(adapter, getJpaProperties(), persistenceUnitManager);
    }

    /**
     * 设置实体类所在位置
     */
    @Bean(name = ENTITY_MANAGER_FACTORY_BEAN_NAME)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier(ENTITY_MANAGER_FACTORY_BUILDER_BEAN_NAME) EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages(ENTITY_PREFIX)
                .persistenceUnit(PERSISTENCE_UNIT_BEAN_NAME)
                .properties(getJpaProperties())
                .build();
    }

    @Bean(name = TRANSACTION_MANAGER_BEAN_NAME)
    public PlatformTransactionManager transactionManager(@Qualifier(ENTITY_MANAGER_FACTORY_BUILDER_BEAN_NAME) EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactory(builder).getObject());
    }

}