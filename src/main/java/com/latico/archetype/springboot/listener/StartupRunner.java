package com.latico.archetype.springboot.listener;

import com.latico.archetype.springboot.config.yaml.YamlBizConfig;
import com.latico.archetype.springboot.timerjob.DemoQuartzJob;
import com.latico.archetype.springboot.timerjob.StatisticsJob;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import com.latico.commons.timer.quartz.QuartzManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * <PRE>
 * 程序启动完成后执行任务,如果有多个，可以指定执行顺序
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-09-25 22:05
 * @Version: 1.0
 */
@Component
@Order(value=1)
public class StartupRunner implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(StartupRunner.class);
    @Override
    public void run(String... args) throws Exception {
        LOG.info("程序启动完成后执行任务");
        startTimer();
    }

    /**
     * 启动定时执行器
     */
    public void startTimer() {
        LOG.info("开始启动定时器QuartzManager定时执行相关任务");

//        启动统计工作
        QuartzManager.getInstance().addJob(StatisticsJob.JOB_NAME,
                StatisticsJob.class, YamlBizConfig.getInstance().getStatsticsCron());

//        使用最简单的构造方式，如果有特殊需求，可以使用其他更多入参的方法，配置3秒钟执行一次
        QuartzManager.getInstance().addJob(DemoQuartzJob.JOB_NAME,
                DemoQuartzJob.class, "0/5 * * * * ?");

        LOG.info("启动定时器QuartzManager完成");
    }
}
