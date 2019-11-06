package com.latico.archetype.springboot.common.statistics;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <PRE>
 * 统计数据
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-11-06 10:47
 * @Version: 1.0
 */
public class StatisticsData {
    /**
     * instance 单例实例
     */
    private static volatile StatisticsData instance = null;

    /**
     * key是统计的key，value是统计的数量
     */
    private Map<String, AtomicLong> statisticsResult = new ConcurrentHashMap<>();

    private StatisticsData() {
    }

    /**
     * 获取单例，同步双重校验的好处在于，兼顾了效率、支持延迟加载、可以再创建对象后，
     * 调用方法进行初始化，而不需要在构造方法初始化，因为有些参数的加载，需要对象创建成功后进行
     *
     * @return
     */
    public static StatisticsData getInstance() {
        if (instance == null) {
            synchronized (StatisticsData.class) {
                if (instance == null) {
                    instance = new StatisticsData();
                }
            }
        }
        return instance;
    }

    /**
     * 自增一个和拿到结果
     * @param name
     * @return
     */
    public long incrementAndGet(String name) {
        AtomicLong atomicLong = statisticsResult.get(name);
        if (atomicLong == null) {
            atomicLong = new AtomicLong(0);
            statisticsResult.put(name, atomicLong);
        }
        return atomicLong.incrementAndGet();
    }

    /**
     * 重置,清理
     * @param name
     * @return
     */
    public void reset(String name) {
        AtomicLong atomicLong = statisticsResult.get(name);
        if (atomicLong == null) {
            atomicLong = new AtomicLong(0);
            statisticsResult.put(name, atomicLong);
        } else {
            atomicLong.set(0);
        }
    }

    /**
     * 拿到统计结果
     * @return
     */
    public Map<String, AtomicLong> getStatisticsResult() {
        return statisticsResult;
    }

}
