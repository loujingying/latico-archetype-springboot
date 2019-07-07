package com.latico.archetype.springboot.service;

import com.latico.archetype.springboot.config.ConfigTestApplication;
import com.latico.archetype.springboot.dao.DaoTestApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <PRE>
 *  DAO测试程序启动入口类
 *
 * </PRE>
 * @Author: latico
 * @Date: 2019-03-13 22:06:01
 * @Version: 1.0
 */
@SpringBootApplication(scanBasePackageClasses = {ServiceTestApplication.class, DaoTestApplication.class, ConfigTestApplication.class})
public class ServiceTestApplication {
    /**
     * springboot启动
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ServiceTestApplication.class);
        application.run(args);
    }

}
