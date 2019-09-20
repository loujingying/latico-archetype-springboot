package com.latico.archetype.springboot.dao.primary.mapper;

import com.latico.archetype.springboot.common.dao.AbstractDao;
import com.latico.archetype.springboot.dao.primary.entity.Demo;
import org.springframework.stereotype.Repository;

/**
 * 基本的JPA的DAO
 */
@Repository
public class DemoDaoRepository extends AbstractDao<Demo, Integer> {

}