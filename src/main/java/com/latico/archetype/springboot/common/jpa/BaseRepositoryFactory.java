package com.latico.archetype.springboot.common.jpa;

import com.latico.archetype.springboot.common.jpa.impl.BaseRepositoryImpl;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.RepositoryMetadata;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * 自定义持久化工厂
 * @param <T>
 * @param <I>
 */
public class BaseRepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory {

        private final EntityManager em;

        public BaseRepositoryFactory(EntityManager em) {
            super(em);
            this.em = em;
        }

        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
//            指定自定义实现类
            return BaseRepositoryImpl.class;
        }
    }