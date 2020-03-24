package com.latico.archetype.springboot.kafka.consumer.impl;

import com.latico.archetype.springboot.kafka.config.KafkaConstants;
import com.latico.archetype.springboot.kafka.consumer.MyKafkaConsumer;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @author: latico
 * @date: 2020-03-10 16:19
 * @version: 1.0
 */
@Service
public class DemoMyKafkaConsumerImpl implements MyKafkaConsumer<String, String> {

    private static final Logger LOG = LoggerFactory.getLogger(DemoMyKafkaConsumerImpl.class);

    @Override
    @KafkaListener(topics = {KafkaConstants.TOPIC_DEMO})
    public void listen(ConsumerRecord<String, String> record) {
        LOG.info("收到消息:record ={}", record);
    }
}
