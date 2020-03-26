package com.latico.archetype.springboot.activemq.consumer.impl;

import com.latico.archetype.springboot.activemq.config.ActiveMqConfig;
import com.latico.archetype.springboot.activemq.consumer.ActiveMqConsumer;
import com.latico.archetype.springboot.activemq.consumer.ActiveMqConsumerWithTranspond;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @author: latico
 * @date: 2020-03-26 16:33
 * @version: 1.0
 */
@Component
public class DemoActiveMqConsumerImpl implements ActiveMqConsumer<String> {
    /** 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(DemoActiveMqConsumerImpl.class);

    /**
     * 使用JmsListener配置消费者监听的队列
     * @param msg 收到的消息
     */
    @JmsListener(destination = ActiveMqConfig.demoQueueName)
    @Override
    public void listenReceiveMsg(String msg) {
        LOG.info("成功接收：{}", msg);
    }
}
