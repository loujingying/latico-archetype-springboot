package com.latico.archetype.springboot.activemq.consumer;

/**
 * <PRE>
 * 消费端
 * </PRE>
 * @param <MSG> 接收的数据的格式
 * @author: latico
 * @date: 2020-03-26 16:32
 * @version: 1.0
 */
public interface ActiveMqConsumer<MSG> {

    /**
     * 监听接收消息
     * 实现类可以在这个方法加:{@link org.springframework.messaging.handler.annotation.SendTo}注解，比如： @SendTo("SecondQueue")，
     * 此时会将此方法返回的数据, 转发写入到 SecondQueue 中去.
     * @param msg 收到的消息
     */
    public void listenReceiveMsg(MSG msg);
}
