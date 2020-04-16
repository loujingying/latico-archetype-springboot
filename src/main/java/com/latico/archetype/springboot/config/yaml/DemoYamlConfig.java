package com.latico.archetype.springboot.config.yaml;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <PRE>
 * config/application-biz.yaml中的demo-yaml-config配置对应
 * 该配置项，演示了众多场景下的配置情况，应该满足大部分需求
 * <p>
 * demo-yaml-config的配置，因为不能让外界修改配置数据，所以把类放到这里
 * </PRE>
 *
 * @author: latico
 * @date: 2019-06-30 15:10:58
 * @version: 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "demo-yaml-config")
public class DemoYamlConfig {
    private String statsticsCron;
    private String value;
    private String[] valueArray;
    private List<String> valueList;
    private Map<String, String> valueMap;
    private List<Map<String, String>> valueMapList;
}