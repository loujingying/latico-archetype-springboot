package com.latico.archetype.springboot.controller;

import com.latico.archetype.springboot.activemq.producer.ActiveMqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;

/*
 * @author uv
 * @date 2018/9/15 14:54
 *
 */
@RestController
@RequestMapping("demo/activemq")
public class DemoActiveMqController {

    @Autowired
    private ActiveMqProducer activeMqProducer;

    @RequestMapping("send")
    public boolean send(String msg) {
        return activeMqProducer.send(msg);
    }
}