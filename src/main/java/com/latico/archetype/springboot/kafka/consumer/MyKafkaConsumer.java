package com.latico.archetype.springboot.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * <PRE>
 *  消费者
 * </PRE>
 * @author: latico
 * @date: 2020-03-10 16:19:00
 * @version: 1.0
 */
public interface MyKafkaConsumer<K, V> {

    /**
     * 监听接收消息
     * @param record 收到的消息
     */
    public void listenReceiveMsg(ConsumerRecord<K, V> record);
}