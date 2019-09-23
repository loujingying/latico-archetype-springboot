package com.latico.archetype.springboot.dao.secondary.repository;

import com.latico.archetype.springboot.common.jpa.BaseRepository;
import com.latico.archetype.springboot.dao.secondary.entity.Demo2;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface Demo2Repository extends BaseRepository<Demo2, String> {
    /**
     * 查询第一个
     * @param taskId
     * @return
     */
    Demo2 queryFirstByTaskId(Integer taskId);

    /**
     * 根据状态字段查询
     * @param execStatus
     * @param execType
     * @return
     */
    List<Demo2> queryAllByExecStatusAndExecTypeIn(Integer execStatus, List<String> execType);

    /**
     * 更新数据
     * @param newObj
     * @return
     */
    @Modifying
    @Transactional
    @Query("update Demo2 obj set obj.execStatus = :#{#newObj.execStatus}, obj.statusDescr = :#{#newObj.statusDescr},obj.updateTime = :#{#newObj.updateTime} where obj.taskId = :#{#newObj.taskId}")
    int updateStatusAndTime(@Param("newObj") Demo2 newObj);

    /**
     * 获取taskId的最大值
     * @return
     */
    @Query(value = "select max(task_id) max from demo", nativeQuery = true)
    Integer getTaskIdMax();
}