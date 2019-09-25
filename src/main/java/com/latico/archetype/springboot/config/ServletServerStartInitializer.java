package com.latico.archetype.springboot.config;

import com.latico.archetype.springboot.Application;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * <PRE>
 *  如果是部署到外部Servlet服务器容器中启动时会读取这里的方式进行启动，如果是使用jar方式启动就是使用Application.main
 * </PRE>
 * @Author: latico
 * @Date: 2019-09-25 17:30:29
 * @Version: 1.0
 */
public class ServletServerStartInitializer extends SpringBootServletInitializer {
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(Application.class);
    }
}
