package com.latico.archetype.springboot.kafka.consumer;
 
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
 
import java.util.Optional;

/**
 * <PRE>
 *  消费者
 * </PRE>
 * @Author: latico
 * @Date: 2020-03-10 16:19:00
 * @Version: 1.0
 */
public interface DemoKafkaConsumer {
    public void listen(ConsumerRecord<?, ?> record);
}