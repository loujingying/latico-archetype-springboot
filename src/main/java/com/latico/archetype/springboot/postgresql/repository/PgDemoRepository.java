package com.latico.archetype.springboot.postgresql.repository;

import com.latico.archetype.springboot.common.jpa.BaseRepository;
import com.latico.archetype.springboot.postgresql.entity.PgDemo;
import org.springframework.stereotype.Repository;

/**
 * <PRE>
 *
 * </PRE>
 * @author: latico
 * @date: 2020-04-08 15:38:20
 * @version: 1.0
 */
@Repository
public interface PgDemoRepository extends BaseRepository<PgDemo, Integer> {
    
}
