package com.latico.archetype.springboot.common.util;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <PRE>
 * JPA工具类
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-09-20 13:59
 * @Version: 1.0
 */
public class JpaUtils {

    /**
     * 根据Id注解字段，直接插入单条数据
     * @param entityManager
     * @param entity
     * @param <T>
     * @return
     */
    public <T> T insert(EntityManager entityManager, T entity) {
        entityManager.persist(entity);
        return entity;
    }

    /**
     * 根据Id注解字段，直接更新单条数据
     * @param entityManager
     * @param entity
     * @param <T>
     * @return
     */
    public <T> T update(EntityManager entityManager, T entity) {
        return entityManager.merge(entity);
    }

    /**
     * 根据Id注解字段，直接批量插入
     * @param entityManager
     * @param entitys
     * @param batchSize
     * @param <T>
     * @return
     */
    public <T> Iterable<T> insertBatch(EntityManager entityManager, Iterable<T> entitys, final int batchSize) {
        Iterator<T> iterator = entitys.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            index++;
            entityManager.persist(iterator.next());
            if (index % batchSize == 0) {
                entityManager.flush();
            }
        }

        if (index % batchSize != 0) {
            entityManager.flush();
        }
        return entitys;
    }

    /**
     * 根据Id注解字段，直接批量更新
     * @param entityManager
     * @param entitys
     * @param batchSize
     * @param <T>
     * @return
     */
    public <T> Iterable<T> updateBatch(EntityManager entityManager, Iterable<T> entitys, final int batchSize) {
        Iterator<T> iterator = entitys.iterator();
        int index = 0;
        List<T> list = new ArrayList<>();
        while (iterator.hasNext()) {
            index++;
            list.add(entityManager.merge(iterator.next()));
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
