package com.latico.archetype.springboot.listener;

import com.latico.archetype.springboot.service.impl.DemoServiceImpl;
import com.latico.archetype.springboot.timerjob.DemoQuartzJob;
import com.latico.commons.common.util.logging.LogUtils;
import com.latico.commons.timer.quartz.QuartzManager;
import org.junit.Test;

public class StartupListenerTest {

    @Test
    public void startTimer() {
        LogUtils.loadLogBackConfig("./config/logback-spring.xml");
        StartupListener.startTimer();
        DemoServiceImpl demo = new DemoServiceImpl();
        System.out.println(demo.serverTimeStr());
    }

    /**
     *
     */
    @Test
    public void test1(){
        LogUtils.loadLogBackConfig("./config/logback-spring.xml");
        DemoServiceImpl demo = new DemoServiceImpl();
        System.out.println(demo.serverTimeStr());
    }

    /**
     *
     */
    @Test
    public void test2(){
        QuartzManager.getInstance().addJob(DemoQuartzJob.JOB_NAME, DemoQuartzJob.class, "0/5 * * * * ?");
    }

}