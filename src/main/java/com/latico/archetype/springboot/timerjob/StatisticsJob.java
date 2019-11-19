package com.latico.archetype.springboot.timerjob;

import com.latico.archetype.springboot.common.statistics.DataStatistics;
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
    private static final Logger LOG = LoggerFactory.getLogger("statistics");
    /**
     * 作业的名称
     */
    public static final String JOB_NAME = "StatisticsJob";

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        Map<String, Map<String, AtomicLong>> statisticsResult = DataStatistics.getInstance().getStatisticsResult();

        if (!statisticsResult.isEmpty()) {
            for (Map.Entry<String, Map<String, AtomicLong>> entry : statisticsResult.entrySet()) {
                if (MapUtils.isEmpty(entry.getValue())) {
                    continue;
                }
                String groupName = entry.getKey();
                StringBuilder sb = new StringBuilder();
                sb.append("[").append(DateTimeUtils.getSystemDate(DateTimeUtils.FORMAT_YMDHMS)).append("]");
                sb.append("[").append(groupName).append("]").append("[");
                sb.append(MapUtils.mapToStr(entry.getValue(), ",", "="));
                String str = sb.toString();
                str = str.substring(0, str.length() - 1);
                LOG.info(str + "]");
            }
            DataStatistics.getInstance().resetCurrentPeriodCount();
        }

    }
}
