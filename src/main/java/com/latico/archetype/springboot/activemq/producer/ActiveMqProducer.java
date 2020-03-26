package com.latico.archetype.springboot.activemq.producer;

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
public interface ActiveMqProducer<T> {

    /**
     * 发送消息
     * 内部指定队列
     * @param msg
     * @return
     */
    boolean send(T msg);

    /**
     * 发送消息
     * 指定队列名称
     * @param destinationQueueName 队列名称
     * @param msg
     * @return
     */
    boolean send(String destinationQueueName, T msg);

    /**
     * 发送消息
     * 指定队列
     * @param destinationQueue 目的队列
     * @param msg
     * @return
     */
    boolean send(Queue destinationQueue, T msg);

}
