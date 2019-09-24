package com.latico.archetype.springboot.async;

import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <PRE>
 * 异步配置，定义线程池，异步任务执行的时候内部会使用
 * 注解来启动，如果不想启动，就关掉这个注解@EnableAsync
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-09-24 15:06
 * @Version: 1.0
 */
@Configuration
@EnableAsync
public class AsyncConfigurerImpl implements AsyncConfigurer {

    private static final Logger LOG = LoggerFactory.getLogger(AsyncConfigurerImpl.class);

    /**
     * @return 定义一个线程池
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(5);
        threadPoolTaskExecutor.setMaxPoolSize(10);
//        队列容量一般是最大线程的2-3倍
        threadPoolTaskExecutor.setQueueCapacity(30);
//        使用调用者策略，当队列和最大线程满了之后，调用者线程自身加入一起工作
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.initialize();
        LOG.info("初始化线程池完成:{}", threadPoolTaskExecutor.toString());
        return threadPoolTaskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        LOG.error("异步执行发生异常");
        return null;
    }
}
