package com.latico.archetype.springboot.dao;

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
@SpringBootApplication
public class DaoTestApplication {
    /**
     * springboot启动
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DaoTestApplication.class);
        application.run(args);
    }

}
