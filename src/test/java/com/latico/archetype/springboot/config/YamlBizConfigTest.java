package com.latico.archetype.springboot.config;

import com.latico.archetype.springboot.config.yaml.YamlBizConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ConfigTestApplication.class})
public class YamlBizConfigTest {

    @Test
    public void getConfigAttributes() {
        System.out.println(YamlBizConfig.getInstance().toString());
    }
}