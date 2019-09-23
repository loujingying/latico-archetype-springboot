package com.latico.archetype.springboot.dao.secondary.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.latico.archetype.springboot.dao.secondary.entity.Demo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author latico
 * @since 2019-02-26
 */
public interface Demo2Mapper extends BaseMapper<Demo> {
    /**
     * @return
     */
    List<Demo> findAll();
}
