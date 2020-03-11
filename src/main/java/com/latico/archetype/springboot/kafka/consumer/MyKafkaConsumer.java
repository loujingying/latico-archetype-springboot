package com.latico.archetype.springboot.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * <PRE>
 *  消费者
 * </PRE>
 * @Author: latico
 * @Date: 2020-03-10 16:19:00
 * @Version: 1.0
 */
public interface MyKafkaConsumer<K, V> {

    /**
     * 监听接收消息
     * @param record
     */
    public void listen(ConsumerRecord<K, V> record);
}