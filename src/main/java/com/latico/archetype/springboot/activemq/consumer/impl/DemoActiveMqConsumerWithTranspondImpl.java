package com.latico.archetype.springboot.activemq.consumer.impl;

import com.latico.archetype.springboot.activemq.config.ActiveMqConfig;
import com.latico.archetype.springboot.activemq.consumer.ActiveMqConsumerWithTranspond;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
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
public class DemoActiveMqConsumerWithTranspondImpl implements ActiveMqConsumerWithTranspond<String, String> {
    /** 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(DemoActiveMqConsumerWithTranspondImpl.class);

    /**
     * 使用JmsListener配置消费者监听的队列
     * 有返回值的，是二次转发的接口类型，需要加:{@link org.springframework.messaging.handler.annotation.SendTo}注解，比如： @SendTo("SecondQueue")，
     * 此时会将此方法返回的数据, 转发写入到 SecondQueue 中去.
     * @param msg 收到的消息
     */
    @SendTo(ActiveMqConfig.demoSecondQueue)
    @JmsListener(destination = ActiveMqConfig.demoWithTranspondQueueName)
    @Override
    public String listenReceiveMsg(String msg) {
        LOG.info("成功接收：{}", msg);
        return "第一次处理后的数据:" + msg;
    }
}
