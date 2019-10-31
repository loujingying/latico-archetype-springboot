package com.latico.archetype.springboot.scheduled;

import com.latico.archetype.springboot.async.DemoAsyncTaskService;
import com.latico.archetype.springboot.common.util.DateTimeUtils;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * <PRE>
 *     演示调度器
 * 用@EnableScheduling注解打开调度器，如果不需要，就关了，默认打开
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-09-24 14:52
 * @Version: 1.0
 */
@Configuration
@EnableScheduling
public class DemoScheduled {

    private static final Logger LOG = LoggerFactory.getLogger(DemoScheduled.class);

    @Autowired
    DemoAsyncTaskService demoAsyncTaskService;

    @Scheduled(fixedRate = 10000)
    public void reportCurrentTimeFixedRate() {
        Timestamp sysTimestamp = DateTimeUtils.getSysTimestamp();
        LOG.info("固定10秒执行一次报告当前时间:{}", sysTimestamp);

        demoAsyncTaskService.asyncExec(sysTimestamp);
    }

    @Scheduled(fixedDelay = 15000)
    public void reportCurrentTimeFixedDelay() {
        Timestamp sysTimestamp = DateTimeUtils.getSysTimestamp();
        LOG.info("执行完后15秒执行一次,报告当前时间:{}", sysTimestamp);

        demoAsyncTaskService.asyncExec(sysTimestamp);
    }

    @Scheduled(cron = "0/20 * * * * ?")
    public void reportCurrentTimeCron() {
        Timestamp sysTimestamp = DateTimeUtils.getSysTimestamp();
        LOG.info("cron方式执行,每20秒执行一次,报告当前时间:{}", sysTimestamp);

        demoAsyncTaskService.asyncExec(sysTimestamp);
    }
}
