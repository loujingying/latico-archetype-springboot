package com.latico.archetype.springboot.invoker.type_springcloud;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientTestApplication.class)
public class TestDBControllerClientTest {
    @Autowired
    TestControllerClient testControllerClient;
    @Test
    public void selectDemo() {

        System.out.println(testControllerClient.selectDemo());
        System.out.println(testControllerClient.selectDemo2());

    }
}