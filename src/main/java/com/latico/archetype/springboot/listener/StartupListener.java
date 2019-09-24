package com.latico.archetype.springboot.listener;

import com.latico.archetype.springboot.timerjob.DemoQuartzJob;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import com.latico.commons.timer.quartz.QuartzManager;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * <PRE>
 * 默认的启动监听器，如果有需要，可以多个，继承ApplicationListener即可
 * </PRE>
 * @Author: latico
 * @Date: 2019-05-09 18:21:03
 * @Version: 1.0
 */
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(StartupListener.class);
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        doService();
    }

    /**
     * TODO
     * 启动业务处理
     */
    private void doService() {
        LOG.info("开始启动自动任务");
        startTimer();
        LOG.info("自动任务启动完成");
    }

    /**
     * 启动定时执行器
     */
    public static void startTimer() {
        LOG.info("开始启动定时器QuartzManager定时执行相关任务");

//        使用最简单的构造方式，如果有特殊需求，可以使用其他更多入参的方法，配置3秒钟执行一次
        QuartzManager.getInstance().addJob(DemoQuartzJob.JOB_NAME,
                DemoQuartzJob.class, "0/3 * * * * ?");

        LOG.info("启动定时器QuartzManager完成");
    }

}