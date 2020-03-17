package com.latico.archetype.springboot.scheduled;

import com.latico.archetype.springboot.common.statistics.DataStatistics;
import com.latico.archetype.springboot.common.statistics.StatisticsData;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <PRE>
 * 统计打印
 * </PRE>
 *
 * @Author: latico
 * @Date: 2020-03-17 11:27
 * @Version: 1.0
 */
@Configuration
public class StatisticsScheduled {

    @Scheduled(cron = "0 * * * * ?")
    public void statisticsData() {
        Map<String, Map<String, AtomicLong>> statisticsResult = DataStatistics.getInstance().getStatisticsResult();
        StatisticsData.printLog(statisticsResult);
    }
}
