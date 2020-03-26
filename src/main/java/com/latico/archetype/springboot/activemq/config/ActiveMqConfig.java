package com.latico.archetype.springboot.activemq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

/**
 * <PRE>
 * 启动JMS配置
 * </PRE>
 *
 * @author: latico
 * @date: 2020-03-26 16:24
 * @version: 1.0
 */
@Configuration
@EnableJms
public class ActiveMqConfig {

    /**
     * 队列名称
     */
    public static final String demoQueueName = "demoQueue";

    /**
     * 带转发响应的队列名
     */
    public static final String demoWithTranspondQueueName = "demoQueue2";
    /**
     * 第二队列，用于带响应的消费者接收消息后，再进行消息转发的目的队列
     */
    public static final String demoSecondQueue = "demoSecondQueue";
    /**
     * 定义存放消息的队列
     * @return
     */
    @Bean(name = demoQueueName)
    public ActiveMQQueue demoActiveMqQueue() {
        return new ActiveMQQueue(demoQueueName);
    }
}
