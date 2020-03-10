package com.latico.archetype.springboot.controller;

import com.latico.archetype.springboot.kafka.bean.DemoKafkaMsg;
import com.latico.archetype.springboot.kafka.producer.DemoKafkaProducer;
import com.latico.archetype.springboot.kafka.producer.impl.DemoKafkaProducerImpl;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * <PRE>
 *
 * </PRE>
 * @Author: latico
 * @Date: 2020-03-10 17:08:47
 * @Version: 1.0
 */
@RestController
@RequestMapping("demo/kafka")
public class DemoKafkaController {
    private static final Logger LOG = LoggerFactory.getLogger(DemoKafkaController.class);

    @Autowired
    private DemoKafkaProducer demoKafkaProducer;

    @RequestMapping("send")
    public boolean send(@RequestBody DemoKafkaMsg demoKafkaMsg) {
        SendResult<String, String> sendResult = demoKafkaProducer.send(demoKafkaMsg);
        LOG.info(sendResult);
        return true;
    }

}