package com.latico.archetype.springboot.config;

import com.latico.archetype.springboot.dao.DaoTestApplication;
import com.latico.commons.spring.extend.ApplicationContextAwareImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <PRE>
 *  DAO测试程序启动入口类
 *
 * </PRE>
 * @Author: LanDingDong
 * @Date: 2019-03-13 22:06:01
 * @Version: 1.0
 */
@SpringBootApplication(scanBasePackageClasses = {ConfigTestApplication.class, DaoTestApplication.class, ApplicationContextAwareImpl.class})
public class ConfigTestApplication {
    /**
     * springboot启动
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ConfigTestApplication.class);
        application.run(args);
    }

}
