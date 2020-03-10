package com.latico.archetype.springboot.kafka.producer.impl;

import com.latico.archetype.springboot.common.util.JsonUtils;
import com.latico.archetype.springboot.kafka.bean.DemoKafkaMsg;
import com.latico.archetype.springboot.kafka.common.KafkaConstants;
import com.latico.archetype.springboot.kafka.producer.DemoKafkaProducer;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @Author: latico
 * @Date: 2020-03-10 16:25
 * @Version: 1.0
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class DemoKafkaProducerImpl implements DemoKafkaProducer {
    private static final Logger LOG = LoggerFactory.getLogger(DemoKafkaProducerImpl.class);
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public SendResult<String, String> send(DemoKafkaMsg msg) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(KafkaConstants.topic_demo, JsonUtils.objToJson(msg));

        // try {
        //     waitForDone(future);
        // } catch (Exception e) {
        //     LOG.error("", e);
        // }

        SendResult<String, String> sendResult = null;
        try {
            sendResult = future.get();
        } catch (Exception e) {
            LOG.error("", e);
        }

        return sendResult;
    }

    private void waitForDone(ListenableFuture<SendResult<String, String>> future) throws ExecutionException, InterruptedException {
        SendResult<String, String> sendResult = future.get();
    }
}
