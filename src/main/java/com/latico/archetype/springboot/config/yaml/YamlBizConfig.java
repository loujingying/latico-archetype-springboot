package com.latico.archetype.springboot.config.yaml;

import com.latico.commons.spring.util.SpringUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <PRE>
 * 业务配置，yaml方式的配置，跟application-biz.yaml对应，一个配置文件提供一个单例统一管理，里面具体的配置项可以拆分成对象
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-02-19 15:55
 * @Version: 1.0
 */
public class YamlBizConfig {

    /**
     * 私有单例对象，需要使用volatile，让其他线程直接可见
     */
    private static volatile YamlBizConfig instance;
    private final DemoYamlConfig demoYamlConfig;

    /**
     * 提供给外界获取单例的方法
     *
     * @return 单例对象
     */
    public static YamlBizConfig getInstance() {
        //第一层检测，在锁的外面判断是否为空，效率高
        if (instance == null) {
            //开始进入加锁创建对象
            synchronized (YamlBizConfig.class) {
                //第二层检测，因为第一层检测期间可能其他线程正则创建，但是未创建完成，所以需要在这里进行二次判断
                if (instance == null) {
                    //创建实例
                    instance = new YamlBizConfig();
                }
            }
        }
        return instance;
    }

    /**
     * 私有构造方法，让外界不能创建对象，在这里做初始化逻辑处理
     */
    private YamlBizConfig() {
        this.demoYamlConfig = SpringUtils.getBean(DemoYamlConfig.class);
    }

    /**
     * 获取里面的value字段
     *
     * @return
     */
    public String getDemoYamlConfigValue() {
        return demoYamlConfig.getValue();
    }

    /**
     * 统计任务的表达式
     *
     * @return
     */
    public String getStatsticsCron() {
        return demoYamlConfig.getStatsticsCron();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("YamlBizConfig{");
        sb.append("demoYamlConfig=").append(demoYamlConfig);
        sb.append('}');
        return sb.toString();
    }
}

/**
 * <PRE>
 * config/application-biz.yaml中的demo-yaml-config配置对应
 * 该配置项，演示了众多场景下的配置情况，应该满足大部分需求
 * <p>
 * demo-yaml-config的配置，因为不能让外界修改配置数据，所以把类放到这里
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-06-30 15:10:58
 * @Version: 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "demo-yaml-config")
class DemoYamlConfig {
    private String statsticsCron;
    private String value;
    private String[] valueArray;
    private List<String> valueList;
    private HashMap<String, String> valueMap;
    private List<Map<String, String>> valueMapList;
}
