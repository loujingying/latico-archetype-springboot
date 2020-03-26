package com.latico.archetype.springboot.activemq.producer.impl;

import com.latico.archetype.springboot.activemq.config.ActiveMqConfig;
import com.latico.archetype.springboot.activemq.producer.ActiveMqProducer;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @author: latico
 * @date: 2020-03-26 16:41
 * @version: 1.0
 */
@Component
public class DemoActiveMqProducerImpl implements ActiveMqProducer<String> {
    /**
     * 注入存放消息的队列，用于下列方法一
     */
    @Autowired
    @Qualifier(ActiveMqConfig.demoQueueName)
    private ActiveMQQueue queue;

    /**
     * 注入springboot封装的工具类
     */
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Override
    public boolean send(String msg) {
        jmsMessagingTemplate.convertAndSend(queue, msg);
        return true;
    }

    @Override
    public boolean send(String destinationQueueName, String msg) {
        jmsMessagingTemplate.convertAndSend(destinationQueueName, msg);
        return true;
    }

    @Override
    public boolean send(Queue destinationQueue, String msg) {
        jmsMessagingTemplate.convertAndSend(destinationQueue, msg);
        return true;
    }

}
