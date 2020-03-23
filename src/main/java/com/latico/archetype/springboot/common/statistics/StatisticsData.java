package com.latico.archetype.springboot.common.statistics;

import com.latico.commons.common.util.collections.MapUtils;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import com.latico.commons.common.util.time.DateTimeUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <PRE>
 * 统计数据
 * </PRE>
 *
 * @author: latico
 * @date: 2019-11-06 10:47
 * @version: 1.0
 */
public class StatisticsData {
    private static final Logger LOG_STATISTICS = LoggerFactory.getLogger("statistics");
    /**
     * instance 单例实例
     */
    private static volatile StatisticsData instance = null;

    private static final String DEFAULT_GROUP_NAME = "默认组";
    /**
     * 第一维key是组名称，第二维key是统计的key，value是统计的数量
     */
    private Map<String, Map<String, AtomicLong>> statisticsResult = new ConcurrentHashMap<>();

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
     * 添加
     * @param name
     * @param count 指定值
     * @return
     */
    public long addAndGet(String name, long count) {
        return addAndGet(DEFAULT_GROUP_NAME, name, count);
    }
    /**
     * 自增一个和拿到结果
     * @param groupName
     * @param name
     * @param count
     * @return
     */
    public long addAndGet(String groupName, String name, long count) {
        Map<String, AtomicLong> map = statisticsResult.get(groupName);
        AtomicLong atomicLong = null;
        if (map == null) {
            map = new ConcurrentSkipListMap<>();
            atomicLong = new AtomicLong(0);
            map.put(name, atomicLong);
            statisticsResult.put(groupName, map);
        } else {
            atomicLong = map.get(name);
            if (atomicLong == null) {
                atomicLong = new AtomicLong(0);
                map.put(name, atomicLong);
            }
        }
        return atomicLong.addAndGet(count);
    }

    /**
     * 自增
     * @param name
     * @return
     */
    public long incrementAndGet(String name) {
        return addAndGet(DEFAULT_GROUP_NAME, name, 1);
    }
    /**
     * 自增一个和拿到结果
     * @param groupName
     * @param name
     * @return
     */
    public long incrementAndGet(String groupName, String name) {
        return addAndGet(groupName, name, 1);
    }

    /**
     * 重置,清理
     * @param name
     */
    public void reset(String name) {
        reset(DEFAULT_GROUP_NAME, name);
    }
    /**
     * 重置,清理
     * @param groupName
     * @param name
     */
    public void reset(String groupName, String name) {
        Map<String, AtomicLong> map = statisticsResult.get(groupName);
        if (map != null) {
            AtomicLong atomicLong = map.get(name);
            if (atomicLong != null) {
                atomicLong.set(0);
            }
        }
    }

    /**
     * 拿到统计结果
     * @return
     */
    public Map<String, Map<String, AtomicLong>> getStatisticsResult() {
        return statisticsResult;
    }

    /**
     * 打印数据到统计日志到statistics.log中，需要检查logback-spring.xml是否配置了statistics日志
     * @param statisticsResult 第一维key是组分类，第二维是组内的任务名称，值是数量
     */
    public static void printLog(Map<String, Map<String, AtomicLong>> statisticsResult) {
        if (!statisticsResult.isEmpty()) {
            String systemDate = DateTimeUtils.getSystemDate(DateTimeUtils.FORMAT_YMDHMS);
            for (Map.Entry<String, Map<String, AtomicLong>> entry : statisticsResult.entrySet()) {
                if (MapUtils.isEmpty(entry.getValue())) {
                    continue;
                }
                String groupName = entry.getKey();
                StringBuilder sb = new StringBuilder();
                sb.append("[").append(systemDate).append("]");
                sb.append("[").append(groupName).append("]").append("[");
                sb.append(MapUtils.mapToStr(entry.getValue(), ",", "="));
                String str = sb.toString();
                str = str.substring(0, str.length() - 1);
                LOG_STATISTICS.info(str + "]");
            }
        }
    }
}
