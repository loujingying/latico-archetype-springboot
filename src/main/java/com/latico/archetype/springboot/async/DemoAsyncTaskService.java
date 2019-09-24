package com.latico.archetype.springboot.async;

import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * <PRE>
 *
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-09-24 15:16
 * @Version: 1.0
 */
@Component
public class DemoAsyncTaskService {

    private static final Logger LOG = LoggerFactory.getLogger(DemoAsyncTaskService.class);

    /**
     * 添加一个异步执行的方法，会丢进线程池中执行
     * @param date
     */
    @Async
    public void asyncExec(Date date) {
        LOG.info("异步执行数据:{}", date);
    }

}
