package com.latico.archetype.springboot.common.jpa.impl;

import com.latico.archetype.springboot.common.jpa.BaseRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <PRE>
 * 基本持久化实现类
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-09-23 14:48
 * @Version: 1.0
 */
@SuppressWarnings("ALL")
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {
    /**
     * 实体类
     */
    private final Class<T> entityClass;
    /**
     * 拿到实体管理器，想干嘛就干嘛
     */
    private final EntityManager entityManager;

    /**
     * 必须支持的构造方法
     * @param entityClass
     * @param entityManager
     */
    public BaseRepositoryImpl(Class<T> entityClass, EntityManager entityManager) {
        super(entityClass, entityManager);
        this.entityClass = entityClass;
        this.entityManager = entityManager;
    }

    /**
     * 必须支持的构造方法
     * @param entityInformation
     * @param entityManager
     */
    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.entityClass = (Class<T>)entityInformation.getClass();
    }

    @Override
    public boolean support(String modelType) {
        return entityClass.getName().equals(modelType);
    }


    @Transactional
    @Override
    public T insert(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    @Override
    public T update(T entity) {
        return entityManager.merge(entity);
    }

    @Transactional
    @Override
    public <S extends T> List<S> insertBatch(Iterable<S> entitys, final int batchSize) {
        List<S> result = new ArrayList<S>();
        Iterator<S> iterator = entitys.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            index++;
            S next = iterator.next();
            result.add(next);
            entityManager.persist(next);
            if (index % batchSize == 0) {
                entityManager.flush();
            }
        }
        if (index % batchSize != 0) {
            entityManager.flush();
        }
        return result;
    }

    @Transactional
    @Override
    public <S extends T> List<S> updateBatch(Iterable<S> entitys, final int batchSize) {
        Iterator<S> iterator = entitys.iterator();
        int index = 0;
        List<S> list = new ArrayList<>();
        while (iterator.hasNext()) {

            list.add(entityManager.merge(iterator.next()));

            index++;
            if (index % batchSize == 0) {
                entityManager.flush();
            }
        }

        if (index % batchSize != 0) {
            entityManager.flush();
        }
        return list;
    }
}
