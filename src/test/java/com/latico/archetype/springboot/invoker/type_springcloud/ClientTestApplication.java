package com.latico.archetype.springboot.invoker.type_springcloud;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <PRE>
 *  springcloud客户端测试程序启动入口类
 *
 * 配置不要连接数据库
 *  com.latico.commons.spring路径包括了spring的工具类
 * </PRE>
 * @Author: LanDingDong
 * @Date: 2019-03-13 22:06:01
 * @Version: 1.0
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class, HibernateJpaAutoConfiguration.class})
@ServletComponentScan
//@org.springframework.cloud.netflix.eureka.EnableEurekaClient  //启动连接Eureka注册中心时用到，甚至可以完全用不到，因为添加了Eureka的client包，会自动连接
@EnableFeignClients
public class ClientTestApplication {
    /**
     * springboot启动
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ClientTestApplication.class);
        application.run(args);
    }

}
