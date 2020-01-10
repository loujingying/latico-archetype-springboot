package com.latico.archetype.springboot.dao.primary.repository;

import com.latico.archetype.springboot.common.jpa.BaseRepository;
import com.latico.archetype.springboot.dao.primary.entity.DatabaseLock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <PRE>
 *  数据库锁
 * </PRE>
 * @Author: latico
 * @Date: 2020-01-10 17:28:44
 * @Version: 1.0
 */
@Repository
public interface DatabaseLockRepository extends BaseRepository<DatabaseLock, String> {

    DatabaseLock queryByLockKey(String lockKey);
}
