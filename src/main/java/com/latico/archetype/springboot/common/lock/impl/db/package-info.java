/**
 * <PRE>
 * 依靠传统数据库实现分布式锁
 * 需要使用一个线程进行定时扫描锁是否过期需要释放，假如使用的是悲观锁for update就不需要扫描
 * </PRE>
 *
 * @author: latico
 * @date: 2020-01-10 10:46
 * @version: 1.0
 */
package com.latico.archetype.springboot.common.lock.impl.db;