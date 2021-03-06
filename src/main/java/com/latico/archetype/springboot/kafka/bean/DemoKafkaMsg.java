package com.latico.archetype.springboot.kafka.bean;
 
import lombok.Data;

import java.util.Date;

/**
 * <PRE>
 *  kafka的消息
 * </PRE>
 * @author: latico
 * @date: 2020-03-10 16:11:10
 * @version: 1.0
 */
@Data
public class DemoKafkaMsg {
 
    private String id;
 
    private String msg;
 
    private Date sendTime;
 
}