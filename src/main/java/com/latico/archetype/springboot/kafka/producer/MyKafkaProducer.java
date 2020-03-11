package com.latico.archetype.springboot.kafka.producer;

/**
 * <PRE>
 *  生产者
 * </PRE>
 * @Author: latico
 * @Date: 2020-03-10 16:19:07
 * @Version: 1.0
 */
public interface MyKafkaProducer<T, K, V> {

    /**
     * 发送消息
     * @param msg
     * @return
     */
    boolean send(T msg);
}