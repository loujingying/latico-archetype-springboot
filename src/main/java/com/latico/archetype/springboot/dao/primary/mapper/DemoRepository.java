package com.latico.archetype.springboot.dao.primary.mapper;
 
import com.latico.archetype.springboot.dao.primary.entity.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface DemoRepository extends JpaRepository<Demo, Integer> {

    /**
     * 通过findBy方式
     * @param username
     * @return
     */
    public Demo findByUsername(@Param("username") String username);

    /**
     * 也可以直接使用字段名作为查询方法
     * @param username
     * @return
     */
    public Demo username(@Param("username") String username);

}