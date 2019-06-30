package com.latico.archetype.springboot.timerjob;

import com.latico.archetype.springboot.common.util.ThreadUtils;
import com.latico.commons.timer.quartz.QuartzManager;
import org.junit.Test;

public class DemoQuartzJobTest {

    @Test
    public void execute() {
        QuartzManager.getInstance().addJob(DemoQuartzJob.JOB_NAME, DemoQuartzJob.class, "0/5 * * * * ?");

        ThreadUtils.sleepSecond(100000);
    }

}