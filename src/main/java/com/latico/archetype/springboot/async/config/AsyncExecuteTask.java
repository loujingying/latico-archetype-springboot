package com.latico.archetype.springboot.async.config;

/**
 * <PRE>
 * 异步执行任务类
 * </PRE>
 *
 * @author: latico
 * @date: 2020-02-17 22:28
 * @version: 1.0
 */
@FunctionalInterface
public interface AsyncExecuteTask {
    /**
     * 执行业务方法
     * @return 执行结果状态
     */
    boolean execute();
}
