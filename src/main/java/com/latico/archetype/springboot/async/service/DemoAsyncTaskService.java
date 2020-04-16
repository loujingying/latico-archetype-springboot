package com.latico.archetype.springboot.async.service;

import com.latico.archetype.springboot.async.SpringAsyncExecuter;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


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
     * @param date
     */
    public void asyncExec(Date date) {
        springAsyncExecuter.doTaskByDefaultPool(()->{
            LOG.info("异步执行数据:{}", date);
            return true;
        });
    }



}
