package com.latico.archetype.springboot.async.service;

import com.latico.archetype.springboot.async.config.AsyncConfigurerImpl;
import com.latico.archetype.springboot.async.config.AsyncExecute;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.Future;


/**
 * <PRE>
 * 需要结合{@link AsyncConfigurerImpl}使用
 * </PRE>
 *
 * @author: latico
 * @date: 2019-09-24 15:16
 * @version: 1.0
 */
@Component
public class DemoAsyncTaskService {

    private static final Logger LOG = LoggerFactory.getLogger(DemoAsyncTaskService.class);

    /**
     * 没有回调的方式
     * 添加一个异步执行的方法，会丢进线程池中执行
     * @param date
     */
    @Async
    public void asyncExec(Date date) {
        LOG.info("异步执行数据:{}", date);
    }

    /**
     * 有回调的方式，可以结合{@link AsyncConfigurerImpl#waitFuturesDone(java.util.List)}来实现
     * 像{@link java.util.concurrent.Callable}的方式
     *
     * @param asyncExecute
     * @return
     */
    @Async
    public Future<Boolean> asyncExec(AsyncExecute asyncExecute) {
        boolean status = asyncExecute.execute();
        return AsyncResult.forValue(status);
    }

}
