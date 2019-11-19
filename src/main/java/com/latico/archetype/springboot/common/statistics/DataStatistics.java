package com.latico.archetype.springboot.common.statistics;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <PRE>
 * 数据统计，包括当前周期和总数量
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-11-06 11:03
 * @Version: 1.0
 */
public class DataStatistics {
    private static final String groupName = "统计";
    private static final String currentPeriodSrcCount = "当前周期原始数量";
    private static final String currentPeriodHandleCount = "当前周期处理数量";
    private static final String currentPeriodSendCount = "当前周期发送数量";

    private static final String allSrcCount = "总原始数量";
    private static final String allHandleCount = "总处理数量";
    private static final String allSendCount = "总发送数量";

    private final StatisticsData statisticsData = StatisticsData.getInstance();

    /**
     * instance 单例实例
     */
    private static volatile DataStatistics instance = null;

    private DataStatistics() {
    }

    /**
     * 获取单例，同步双重校验的好处在于，兼顾了效率、支持延迟加载、可以再创建对象后，
     * 调用方法进行初始化，而不需要在构造方法初始化，因为有些参数的加载，需要对象创建成功后进行
     *
     * @return
     */
    public static DataStatistics getInstance() {
        if (instance == null) {
            synchronized (StatisticsData.class) {
                if (instance == null) {
                    instance = new DataStatistics();
                }
            }
        }
        return instance;
    }
    /**
     * 拿到统计结果
     * @return
     */
    public Map<String, Map<String, AtomicLong>> getStatisticsResult() {
        return statisticsData.getStatisticsResult();
    }
    /**
     * 重置当前周期的数量统计
     */
    public void resetCurrentPeriodCount() {
        statisticsData.reset(groupName, currentPeriodSrcCount);
        statisticsData.reset(groupName, currentPeriodHandleCount);
        statisticsData.reset(groupName, currentPeriodSendCount);
    }

    /**
     * 自增一个原始
     */
    public void srcCountIncrement() {
        statisticsData.incrementAndGet(groupName, currentPeriodSrcCount);
        statisticsData.incrementAndGet(groupName, allSrcCount);
    }
    /**
     * 自增一个处理
     */
    public void handleCountIncrement() {
        statisticsData.incrementAndGet(groupName, currentPeriodHandleCount);
        statisticsData.incrementAndGet(groupName, allHandleCount);
    }
    /**
     * 自增一个发送
     */
    public void sendCountIncrement() {
        statisticsData.incrementAndGet(groupName, currentPeriodSendCount);
        statisticsData.incrementAndGet(groupName, allSendCount);
    }


}
