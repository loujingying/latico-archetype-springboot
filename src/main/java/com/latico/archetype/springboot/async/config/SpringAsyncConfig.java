package com.latico.archetype.springboot.async.config;

import com.latico.archetype.springboot.async.SpringAsyncExecuter;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Collection;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @author: latico
 * @date: 2020-04-16 11:14
 * @version: 1.0
 */
@Configuration
public class SpringAsyncConfig {
    /** 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(SpringAsyncConfig.class);

    /**
     * 数据库池
     */
    public static final String DB_POOL_NAME = "db_pool";

    @Bean(DB_POOL_NAME)
    public Executor dbExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setMaxPoolSize(50);
//        队列容量一般是最大线程的2-3倍
        threadPoolTaskExecutor.setQueueCapacity(100);
        threadPoolTaskExecutor.setBeanName(DB_POOL_NAME);
//        使用调用者策略，当队列和最大线程满了之后，调用者线程自身加入一起工作
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.initialize();
        LOG.info("初始化线程池完成:{}", DB_POOL_NAME);
        return threadPoolTaskExecutor;
    }

    /**
     * 公共池异步执行，不等待回调
     * 添加一个异步执行的方法，会丢进线程池中执行
     * @param task 需要被执行的任务
     */
    @Async
    public void asyncExecByCommonPool(AsyncExecuteTask task) {
        task.execute();
    }

    /**
     * 公共池异步执行，不等待回调
     * 添加一个异步执行的方法，会丢进线程池中执行
     * @param task 需要被执行的任务
     */
    @Async(value = DB_POOL_NAME)
    public void asyncExecByDbPool(AsyncExecuteTask task) {
        task.execute();
    }

    /**
     * 公共池
     * 有回调的方式，可以结合{@link SpringAsyncExecuter#waitFuturesDone(Collection)}来实现
     * 像{@link java.util.concurrent.Callable}的方式
     *
     * @param asyncExecute
     * @return
     */
    @Async
    public Future<Boolean> asyncExecWithCallbackByCommonPool(AsyncExecuteTask asyncExecute) {
        boolean status = asyncExecute.execute();
        return AsyncResult.forValue(status);
    }

    /**
     * 数据库池
     * 有回调的方式，可以结合{@link SpringAsyncExecuter#waitFuturesDone(Collection)}来实现
     * 像{@link java.util.concurrent.Callable}的方式
     *
     * @param asyncExecute
     * @return
     */
    @Async
    public Future<Boolean> asyncExecWithCallbackByDbPool(AsyncExecuteTask asyncExecute) {
        boolean status = asyncExecute.execute();
        return AsyncResult.forValue(status);
    }

}
