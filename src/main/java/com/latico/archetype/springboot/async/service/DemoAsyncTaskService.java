package com.latico.archetype.springboot.async.service;

import com.latico.archetype.springboot.async.SpringAsyncExecuter;
import com.latico.archetype.springboot.async.config.AsyncExecuteTask;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


/**
 * <PRE>
 * 使用示例
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-09-24 15:16
 * @Version: 1.0
 */
@Component
public class DemoAsyncTaskService {

    private static final Logger LOG = LoggerFactory.getLogger(DemoAsyncTaskService.class);

    @Autowired
    private SpringAsyncExecuter springAsyncExecuter;

    /**
     * 添加一个异步执行的方法，会丢进线程池中执行
     * @param task
     */
    public void asyncExec(AsyncExecuteTask task) {
        springAsyncExecuter.doTaskByDefaultPool(task);
    }

    /**
     * 批量执行，并等待所有执行完成
     * @param tasks
     */
    public void asyncExec(List<AsyncExecuteTask> tasks) {
        springAsyncExecuter.doTasksAndWaitDoneByDefaultPool(tasks);
    }



}
