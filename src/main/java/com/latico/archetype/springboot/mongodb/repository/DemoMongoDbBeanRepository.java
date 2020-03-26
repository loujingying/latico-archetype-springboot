package com.latico.archetype.springboot.mongodb.repository;

import com.latico.archetype.springboot.mongodb.entity.DemoMongoDbBean;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

/**
 * <PRE>
 *  
 * </PRE>
 * @author: latico
 * @date: 2020-03-26 15:19:51
 * @version: 1.0
 */
public interface DemoMongoDbBeanRepository extends MongoRepository<DemoMongoDbBean, Long> {

    /**
     * Like（模糊查询）
     * {"username" : name} ( name as regex)
     * */
    List<DemoMongoDbBean> findByUsernameLike(String username);

    /**
     * Like（模糊查询）
     * {"username" : name}
     * */
    List<DemoMongoDbBean> findByUsername(String username);

}
