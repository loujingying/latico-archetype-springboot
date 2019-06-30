package com.latico.archetype.springboot.client.type_springcloud;

import com.latico.archetype.springboot.bean.bo.DemoTimeParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientTestApplication.class)
public class DemoControllerClientControllerTest {


    @Autowired
    DemoControllerClient demoControllerClient;

    @Test
    public void testPathVariable() {
        System.out.println(demoControllerClient.testPathVariable("abc"));
    }
    @Test
    public void testRequestParam() {
        System.out.println(demoControllerClient.testRequestParam("abc"));
    }
    @Test
    public void testMultiPathVariable() {
        System.out.println(demoControllerClient.testMultiPathVariable("abc", "jajg"));
    }

    @Test
    public void serverTimeBean(){
        DemoTimeParam demoTimeParam = new DemoTimeParam();
        demoTimeParam.setTime(new Timestamp(System.currentTimeMillis()));
        System.out.println("生成对象：" + demoTimeParam);
        System.out.println(demoControllerClient.serverTimeBean(demoTimeParam));
    }
}