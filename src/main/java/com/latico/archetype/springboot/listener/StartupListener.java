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
 * @author: latico
 * @date: 2019-05-09 18:21:03
 * @version: 1.0
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

        LOG.info("自动任务启动完成");
    }

}