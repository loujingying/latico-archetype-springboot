package com.latico.archetype.springboot.async;

import com.latico.archetype.springboot.async.config.AsyncExecuteTask;
import com.latico.archetype.springboot.async.config.SpringAsyncConfig;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * <PRE>
 * 异步执行器
 * </PRE>
 *
 * @author: latico
 * @date: 2020-04-16 11:05
 * @version: 1.0
 */
@Component
public class SpringAsyncExecuter {
    /** 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(SpringAsyncExecuter.class);

    @Autowired
    private SpringAsyncConfig springAsyncConfig;

    /**
     * 等待所有的future完成
     * @param futureList
     * @param <T>
     */
    public static <T> void waitFuturesDone(Collection<Future<T>> futureList) {
        if (futureList.isEmpty()) {
            return;
        }
        while (true) {
            boolean isEndFlag = true;
            Iterator<Future<T>> iterator = futureList.iterator();
            while (iterator.hasNext()) {
                Future<T> next = iterator.next();
                if (next.isDone()) {
                    iterator.remove();
                } else {
                    isEndFlag = false;
                }
            }
            if (isEndFlag) {
                break;
            }
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {

            }
        }
    }

    /**
     * 公共池异步执行，不等待回调
     * 添加一个异步执行的方法，会丢进线程池中执行
     * @param task 需要被执行的任务
     */
    public void doTaskByCommonPool(AsyncExecuteTask task) {
        springAsyncConfig.asyncExecByCommonPool(task);
    }

    /**
     * 数据库异步执行，不等待回调
     * 添加一个异步执行的方法，会丢进线程池中执行
     * @param task 需要被执行的任务
     */
    public void doTaskByDbPool(AsyncExecuteTask task) {
        springAsyncConfig.asyncExecByDbPool(task);
    }

    /**
     * 做任务，通过公共池
     * 会等待所有任务执行完成
     * @param tasks
     */
    public void doTasksAndWaitDoneByCommonPool(Collection<AsyncExecuteTask> tasks) {
        if (tasks == null || tasks.isEmpty()) {
            return;
        }

        List<Future<Boolean>> futures = new ArrayList<>();
        for (AsyncExecuteTask task : tasks) {
            Future<Boolean> booleanFuture = springAsyncConfig.asyncExecWithCallbackByCommonPool(task);
            futures.add(booleanFuture);
        }

        //等待线程池任务完成
        waitFuturesDone(futures);
    }

    /**
     * 做任务，通过数据库池
     * 会等待所有任务执行完成
     * @param tasks
     */
    public void doTasksAndWaitDoneByDbPool(Collection<AsyncExecuteTask> tasks) {
        if (tasks == null || tasks.isEmpty()) {
            return;
        }

        List<Future<Boolean>> futures = new ArrayList<>();
        for (AsyncExecuteTask task : tasks) {
            Future<Boolean> booleanFuture = springAsyncConfig.asyncExecWithCallbackByDbPool(task);
            futures.add(booleanFuture);
        }

        //等待线程池任务完成
        waitFuturesDone(futures);
    }

}
