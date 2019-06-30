package com.latico.archetype.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <PRE>
 *  该测试类用来进行常规的启动测试,检查项目标准组件是否正常
 *
 * </PRE>
 * @Author: LanDingDong
 * @Date: 2019-06-27 10:54:33
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestComponentApplication.class})
public class ApplicationComponentTests {

    @Test
    public void contextLoads() {
        System.out.println("启动测试完成");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("关闭测试");
    }

}
