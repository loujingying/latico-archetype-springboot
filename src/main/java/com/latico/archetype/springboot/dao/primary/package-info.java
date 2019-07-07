/**
 * <PRE>
 * 主数据源配置
 * 使用了JPA方式
 *
 JPA编写示例：

 1、复杂更新：
 @Modifying
 @Transactional
 @Query("update Test a set " +
 "a.name = :#{#testAre.name} ," +
 "a.age = CASE WHEN :#{#testAre.age} IS NULL THEN a.age ELSE :#{#testAre.age} END ," +
 "a.insertTime = CASE WHEN :#{#testAre.insertTime} IS NULL THEN a.insertTime ELSE :#{#testAre.insertTime} END ," +
 "a.spare =  CASE WHEN :#{#testAre.spare} IS NULL THEN a.spare ELSE :#{#testAre.spare} END " +
 "where a.id = :#{#testAre.id}")
 int update(@Param("testAre") TestAre testAre);

2、更新某几个字段，使用参数序号的方式
 @Modifying
 @Query("update User u set u.firstname = ?1 where u.lastname = ?2")

 int setFixedFirstnameFor(String firstname, String lastname);

3、使用变量引用的方式
 @Modifying(clearAutomatically = true)

 @Query("update RssFeedEntry feedEntry set feedEntry.read =:isRead where feedEntry.id =:entryId")

 void markEntryAsRead(@Param("entryId") Long rssFeedEntryId, @Param("isRead") boolean isRead);

4、执行原生SQL
 @Query(value = "select * from table_car_mark limit 0,10",nativeQuery = true)
 List<CarsMark> findTop10();

  * </PRE>
 *
 * @Author: latico
 * @Date: 2019-05-27 15:53
 * @Version: 1.0
 */
package com.latico.archetype.springboot.dao.primary;