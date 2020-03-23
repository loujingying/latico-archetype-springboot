package com.latico.archetype.springboot.common.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * <PRE>
 * 扩展JpaRepository
 * </PRE>
 *
 * @author: latico
 * @date: 2019-09-23 14:46
 * @version: 1.0
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    /**
     * 是否支持类型
     * @param modelType
     * @return
     */
    boolean support(String modelType);

    /**
     * 根据ID，插入对象
     *
     * @param entity
     * @return
     */
    T insert(T entity);

    /**
     * 根据ID，更新对象
     *
     * @param entity
     * @return
     */
    T update(T entity);


    /**
     * 根据ID，批量插入
     * @param entitys
     * @param batchSize
     * @param <S>
     * @return
     */
    <S extends T> List<S> insertBatch(Iterable<S> entitys, int batchSize);

    /**
     * 根据ID，批量更新
     * @param entitys
     * @param batchSize
     * @param <S>
     * @return
     */
    <S extends T> List<S> updateBatch(Iterable<S> entitys, int batchSize);
}
