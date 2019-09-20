package com.latico.archetype.springboot.common.dao;

import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @param <T>
 * @param <ID>
 */
public interface Dao<T, ID extends Serializable> {
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
    @Transactional
    <S extends T> Iterable<S> insertBatch(Iterable<S> entitys, int batchSize);

    /**
     * 根据ID，批量更新
     * @param entitys
     * @param batchSize
     * @param <S>
     * @return
     */
    @Transactional
    <S extends T> Iterable<S> updateBatch(Iterable<S> entitys, int batchSize);


    /**
     * 根据表的id删除数据
     *
     * @param entity
     */
    void delete(T entity);

    /**
     * 根据id查询
     *
     * @param primaryKey
     * @return
     */
    T findById(ID primaryKey);

    /**
     * 根据表名，字段，参数查询，拼接sql语句
     *
     * @param tablename 表名
     * @param filed     字段名
     * @param o         字段参数
     * @return
     */
    List<T> findBysql(String tablename, String filed, Object o);

    Object findObjiectBysql(String tablename, String filed, Object o);

    /**
     * 多个字段的查询
     *
     * @param tablename 表名
     * @param map       将你的字段传入map中
     * @return
     */
    List<T> findByMoreFiled(String tablename, LinkedHashMap<String, Object> map);

    /**
     * 多字段查询分页
     *
     * @param tablename 表名
     * @param map       以map存储key,value
     * @param start     第几页
     * @param pageNumer 一个页面的条数
     * @return
     */
    List<T> findByMoreFiledpages(String tablename, LinkedHashMap<String, Object> map, int start, int pageNumer);

    /**
     * 一个字段的分页
     *
     * @param tablename 表名
     * @param filed     字段名
     * @param o         字段参数
     * @param start     第几页
     * @param pageNumer 一个页面多少条数据
     * @return
     */
    List<T> findpages(String tablename, String filed, Object o, int start, int pageNumer);



    /**
     * 根据传入的map遍历key,value拼接字符串，以id为条件更新
     *
     * @param tablename 表名
     * @param map       传入参数放入map中
     * @return
     */
    Integer updateMoreFiled(String tablename, LinkedHashMap<String, Object> map);


    /**
     * 根据条件查询总条数返回object类型
     *
     * @param tablename 表名
     * @param map       传入参数放入map中
     * @return
     */
    Object findCount(String tablename, LinkedHashMap<String, Object> map);
}