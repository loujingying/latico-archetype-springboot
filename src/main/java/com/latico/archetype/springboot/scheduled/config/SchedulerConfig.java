package com.latico.archetype.springboot.scheduled.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <PRE>
 * 加了EnableScheduling注解就是启动了定时器
 *
 * 用@EnableScheduling注解打开调度器，如果不需要，就关了，默认打开
 * </PRE>
 *
 * @author: latico
 * @date: 2020-03-24 10:59
 * @version: 1.0
 */
@Configuration
@EnableScheduling
public class SchedulerConfig {
}
