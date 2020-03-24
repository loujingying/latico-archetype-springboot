package com.latico.archetype.springboot.hadoop.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * <PRE>
 *  HDFS配置类
 * </PRE>
 * @author: latico
 * @date: 2020-03-23 16:53:45
 * @version: 1.0
 */
@Data
@Configuration
public class HdfsConfig {
    @Value("${hdfs.url}")
    private String url;
    @Value("${hdfs.username}")
    private String username;
}
