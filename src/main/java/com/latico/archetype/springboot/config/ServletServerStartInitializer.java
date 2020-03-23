package com.latico.archetype.springboot.config;

import com.latico.archetype.springboot.Application;
import com.latico.archetype.springboot.common.util.ResourcesUtils;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;

import java.sql.Timestamp;

/**
 * <PRE>
 * 如果是部署到外部Servlet服务器容器中启动时会读取这里的方式进行启动，如果是使用jar方式启动就是使用Application.main
 * </PRE>
 *
 * @author: latico
 * @date: 2019-09-25 17:30:29
 * @version: 1.0
 */
public class ServletServerStartInitializer extends SpringBootServletInitializer {
    private static final Logger LOG = LoggerFactory.getLogger(ServletServerStartInitializer.class);
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder) {
        //        打开程序状态开关
        Application.APP_RUN_STATUS.set(true);

        long startTime = System.currentTimeMillis();
        System.out.println("开始启动程序,时间点:" + new Timestamp(startTime));
        LOG.info("开始启动程序,时间点:" + new Timestamp(startTime));
//启动程序
        Application.startShutDownHook();

        SpringApplication application = springApplicationBuilder.application();
        //        添加程序启动后执行的监听器，因为spring还未启动，所以不能使用注解注入的方式获取监听器，要自己扫描出来
        Application.addListeners(application);


        return springApplicationBuilder.sources(Application.class);
    }
}
