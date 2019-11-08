package com.latico.archetype.springboot.common.statistics;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <PRE>
 * 告警统计，包括当前周期和总数量
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-11-06 11:03
 * @Version: 1.0
 */
public class AlarmStatistics {
    private static final String groupName = "告警统计";
    private static final String currentPeriodSrcAlarmCount = "当前周期原始数量";
    private static final String currentPeriodHandleAlarmCount = "当前周期处理数量";
    private static final String currentPeriodSendAlarmCount = "当前周期发送数量";

    private static final String allSrcAlarmCount = "总原始数量";
    private static final String allHandleAlarmCount = "总处理数量";
    private static final String allSendAlarmCount = "总发送数量";

    private final StatisticsData statisticsData = StatisticsData.getInstance();

    /**
     * instance 单例实例
     */
    private static volatile AlarmStatistics instance = null;

    private AlarmStatistics() {
    }

    /**
     * 获取单例，同步双重校验的好处在于，兼顾了效率、支持延迟加载、可以再创建对象后，
     * 调用方法进行初始化，而不需要在构造方法初始化，因为有些参数的加载，需要对象创建成功后进行
     *
     * @return
     */
    public static AlarmStatistics getInstance() {
        if (instance == null) {
            synchronized (StatisticsData.class) {
                if (instance == null) {
                    instance = new AlarmStatistics();
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
        return StatisticsData.getInstance().getStatisticsResult();
    }
    /**
     * 重置当前周期的告警数量统计
     */
    public void resetCurrentPeriodAlarmCount() {
        statisticsData.reset(groupName, currentPeriodSrcAlarmCount);
        statisticsData.reset(groupName, currentPeriodHandleAlarmCount);
        statisticsData.reset(groupName, currentPeriodSendAlarmCount);
    }

    /**
     * 自增一个原始告警
     */
    public void srcAlarmCountIncrement() {
        statisticsData.incrementAndGet(groupName, currentPeriodSrcAlarmCount);
        statisticsData.incrementAndGet(groupName, allSrcAlarmCount);
    }
    /**
     * 自增一个处理告警
     */
    public void handleAlarmCountIncrement() {
        statisticsData.incrementAndGet(groupName, currentPeriodHandleAlarmCount);
        statisticsData.incrementAndGet(groupName, allHandleAlarmCount);
    }
    /**
     * 自增一个发送告警
     */
    public void sendAlarmCountIncrement() {
        statisticsData.incrementAndGet(groupName, currentPeriodSendAlarmCount);
        statisticsData.incrementAndGet(groupName, allSendAlarmCount);
    }
}
