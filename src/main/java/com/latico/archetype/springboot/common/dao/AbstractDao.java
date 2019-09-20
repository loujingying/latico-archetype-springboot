package com.latico.archetype.springboot.common.dao;

import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import com.latico.commons.common.util.reflect.GenericUtils;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.*;

/**
 * 基本的JPA的DAO
 *
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public abstract class AbstractDao<T, ID extends Serializable> implements Dao<T, ID> {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractDao.class);
    public final Class<T> genricClass = GenericUtils.getSuperClassGenricType(this.getClass());
    @PersistenceContext
    protected EntityManager entityManager;

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
    public <S extends T> Iterable<S> insertBatch(Iterable<S> entitys, final int batchSize) {
        Iterator<S> iterator = entitys.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            entityManager.persist(iterator.next());
            index++;
            if (index % batchSize == 0) {
                entityManager.flush();
            }
        }
        if (index % batchSize != 0) {
            entityManager.flush();
        }
        return entitys;
    }

    @Transactional
    @Override
    public <S extends T> Iterable<S> updateBatch(Iterable<S> entitys, final int batchSize) {
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


    @Transactional
    @Override
    public T findById(ID primaryKey) {
        return entityManager.find(genricClass, primaryKey);
    }

    @Transactional
    @Override
    public List<T> findBysql(String tablename, String filed, Object o) {
        String sql = "from " + tablename + " u WHERE u." + filed + "=?";
        LOG.info(sql + "--------sql语句-------------");
        Query query = entityManager.createQuery(sql);
        query.setParameter(1, o);
        List<T> list = query.getResultList();
        entityManager.close();
        return list;
    }

    @Override
    public Object findObjiectBysql(String tablename, String filed, Object o) {
        String sql = "from " + tablename + " u WHERE u." + filed + "=?";
        LOG.info(sql + "--------sql语句-------------");
        Query query = entityManager.createQuery(sql);
        query.setParameter(1, o);

        entityManager.close();
        return query.getSingleResult();
    }

    @Transactional
    @Override
    public List<T> findByMoreFiled(String tablename, LinkedHashMap<String, Object> map) {
        String sql = "from " + tablename + " u WHERE ";
        Set<String> set = null;
        set = map.keySet();
        List<String> list = new ArrayList<>(set);
        List<Object> filedlist = new ArrayList<>();
        for (String filed : list) {
            sql += "u." + filed + "=? and ";
            filedlist.add(filed);
        }
        sql = sql.substring(0, sql.length() - 4);
        LOG.info(sql + "--------sql语句-------------");
        Query query = entityManager.createQuery(sql);
        for (int i = 0; i < filedlist.size(); i++) {
            query.setParameter(i + 1, map.get(filedlist.get(i)));
        }
        List<T> listRe = query.getResultList();
        entityManager.close();
        return listRe;
    }

    @Transactional
    @Override
    public List<T> findByMoreFiledpages(String tablename, LinkedHashMap<String, Object> map, int start, int pageNumber) {
        String sql = "from " + tablename + " u WHERE ";
        Set<String> set = null;
        set = map.keySet();
        List<String> list = new ArrayList<>(set);
        List<Object> filedlist = new ArrayList<>();
        for (String filed : list) {
            sql += "u." + filed + "=? and ";
            filedlist.add(filed);
        }
        sql = sql.substring(0, sql.length() - 4);
        LOG.info(sql + "--------sql语句-------------");
        Query query = entityManager.createQuery(sql);
        for (int i = 0; i < filedlist.size(); i++) {
            query.setParameter(i + 1, map.get(filedlist.get(i)));
        }
        query.setFirstResult((start - 1) * pageNumber);
        query.setMaxResults(pageNumber);
        List<T> listRe = query.getResultList();
        entityManager.close();
        return listRe;
    }

    @Transactional
    @Override
    public List<T> findpages(String tablename, String filed, Object o, int start, int pageNumer) {
        String sql = "from " + tablename + " u WHERE u." + filed + "=?";
        LOG.info(sql + "--------page--sql语句-------------");
        List<T> list = new ArrayList<>();
        Query query = entityManager.createQuery(sql);
        query.setParameter(1, o);
        query.setFirstResult((start - 1) * pageNumer);
        query.setMaxResults(pageNumer);
        list = query.getResultList();
        entityManager.close();

        return list;
    }


    @Transactional
    @Override
    public Integer updateMoreFiled(String tablename, LinkedHashMap<String, Object> map) {
        String sql = "UPDATE " + tablename + " AS u SET ";
        Set<String> set = null;
        set = map.keySet();
        List<String> list = new ArrayList<>(set);
        for (int i = 0; i < list.size() - 1; i++) {
            if (map.get(list.get(i)).getClass().getTypeName() == "java.lang.String") {
                LOG.info("-*****" + map.get(list.get(i)) + "------------" + list.get(i));
                sql += "u." + list.get(i) + "='" + map.get(list.get(i)) + "' , ";
            } else {
                sql += "u." + list.get(i) + "=" + map.get(list.get(i)) + " , ";
            }
        }
        sql = sql.substring(0, sql.length() - 2);
        sql += "where u.id=? ";
        LOG.info(sql + "--------sql语句-------------");
        int resurlt = 0;
        Query query = entityManager.createQuery(sql);
        query.setParameter(1, map.get("id"));
        resurlt = query.executeUpdate();
        return resurlt;
    }

    @Transactional
    @Override
    public void delete(T entity) {
        entityManager.remove(entityManager.merge(entity));
    }

    @Override
    public Object findCount(String tablename, LinkedHashMap<String, Object> map) {
        String sql = "select count(u) from " + tablename + " u WHERE ";
        Set<String> set = null;
        set = map.keySet();
        List<String> list = new ArrayList<>(set);
        List<Object> filedlist = new ArrayList<>();
        for (String filed : list) {
            sql += "u." + filed + "=? and ";
            filedlist.add(filed);
        }
        sql = sql.substring(0, sql.length() - 4);
        LOG.info(sql + "--------sql语句-------------");
        Query query = entityManager.createQuery(sql);
        for (int i = 0; i < filedlist.size(); i++) {
            query.setParameter(i + 1, map.get(filedlist.get(i)));
        }
        return query.getSingleResult();
    }
}