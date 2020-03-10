package com.latico.archetype.springboot.kafka.producer;

import com.latico.archetype.springboot.kafka.bean.DemoKafkaMsg;
import com.latico.commons.common.util.other.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Date;
 
/**
 * <PRE>
 *  生产者
 * </PRE>
 * @Author: latico
 * @Date: 2020-03-10 16:19:07
 * @Version: 1.0
 */
public interface DemoKafkaProducer {

    SendResult<String, String> send(DemoKafkaMsg msg);
}