package com.latico.archetype.springboot.common.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-09-20 13:59
 * @Version: 1.0
 */
public class JpaUtils {
    //创建entityManagerFactory工厂
    protected static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myJpaEntityManagerFactory");

    /**
     * @return
     */
    public static EntityManager createEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
