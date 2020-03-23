package com.latico.archetype.springboot.kafka.producer.impl;

import com.latico.archetype.springboot.common.util.JsonUtils;
import com.latico.archetype.springboot.kafka.bean.DemoKafkaMsg;
import com.latico.archetype.springboot.kafka.common.KafkaConstants;
import com.latico.archetype.springboot.kafka.producer.MyKafkaProducer;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @author: latico
 * @date: 2020-03-10 16:25
 * @version: 1.0
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class DemoMyKafkaProducerImpl implements MyKafkaProducer<DemoKafkaMsg, String, String> {
    private static final Logger LOG = LoggerFactory.getLogger(DemoMyKafkaProducerImpl.class);
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public boolean send(DemoKafkaMsg msg) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(KafkaConstants.TOPIC_DEMO, JsonUtils.objToJson(msg));

        SendResult<String, String> sendResult = null;
        try {
            sendResult = future.get();
        } catch (Exception e) {
            LOG.error("", e);
        }
        LOG.info("打印发送结果:{}", sendResult);

        return true;
    }

}
