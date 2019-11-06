package com.latico.archetype.springboot.timerjob;

import com.latico.archetype.springboot.common.statistics.StatisticsData;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <PRE>
 * 统计工作
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-11-06 10:38
 * @Version: 1.0
 */
public class StatisticsJob implements Job {
    private static final Logger LOG = LoggerFactory.getLogger("statistics");
    /**
     * 作业的名称
     */
    public static final String JOB_NAME = "StatisticsJob";

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        Map<String, AtomicLong> statisticsResult = StatisticsData.getInstance().getStatisticsResult();

        if (!statisticsResult.isEmpty()) {
            for (Map.Entry<String, AtomicLong> entry : statisticsResult.entrySet()) {
                LOG.info("[{}]=[{}]", entry.getKey(), entry.getValue().get());
            }
//            可以在这里重置清除周期性统计数值
        }

    }
}
