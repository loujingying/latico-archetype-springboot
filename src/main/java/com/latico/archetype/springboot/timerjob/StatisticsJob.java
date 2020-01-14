package com.latico.archetype.springboot.timerjob;

import com.latico.archetype.springboot.common.statistics.DataStatistics;
import com.latico.archetype.springboot.common.statistics.StatisticsData;
import com.latico.archetype.springboot.common.util.DateTimeUtils;
import com.latico.archetype.springboot.common.util.MapUtils;
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

    /**
     * 作业的名称
     */
    public static final String JOB_NAME = "StatisticsJob";

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Map<String, Map<String, AtomicLong>> statisticsResult = DataStatistics.getInstance().getStatisticsResult();
        StatisticsData.printLog(statisticsResult);
    }
}
