package com.latico.archetype.springboot.kafka.consumer.impl;

import com.latico.archetype.springboot.kafka.common.KafkaConstants;
import com.latico.archetype.springboot.kafka.consumer.DemoKafkaConsumer;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @Author: latico
 * @Date: 2020-03-10 16:19
 * @Version: 1.0
 */
@Service
public class DemoKafkaConsumerImpl implements DemoKafkaConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(DemoKafkaConsumerImpl.class);
    @Override
    @KafkaListener(topics = {KafkaConstants.topic_demo})
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            LOG.info("record =" + record);
            LOG.info("message =" + message);

        }
    }
}
