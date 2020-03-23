package com.latico.archetype.springboot.config.druid;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * <PRE>
 *  配置Spring监控
 * </PRE>
 * @author: latico
 * @date: 2019-02-27 13:52:46
 * @version: 1.0
 */
@Configuration
@ImportResource(locations={"classpath:config/spring-druid.xml"})
public class DruidStatInterceptor {
}