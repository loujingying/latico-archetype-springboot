package com.latico.archetype.springboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <PRE>
 * 默认主页
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-03-15 10:34:32
 * @Version: 1.0
 */
@RestController
@Configuration
@Api(description = "主页API")
public class HomeController {

    /**
     * 拿到端口配置
     */
    @Value("${server.port}")
    private String serverPort;

    /**
     * 拿到项目上下文路径
     */
    @Value("${server.servlet.context-path}")
    private String serverContextPath;

    @RequestMapping("")
    @ApiOperation("默认主页API")
    public String home() {
        String serverContextPath = this.serverContextPath;
        if (!serverContextPath.startsWith("/")) {
            serverContextPath = "/" + serverContextPath;
        }
        if (!serverContextPath.endsWith("/")) {
            serverContextPath = serverContextPath + "/";
        }

        String ip = "localhost";
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            ip = localHost.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String swaggerUrl = "http://" + ip + ":" + serverPort + serverContextPath + "swagger-ui.html";
        String swaggerLink = "<a href=\"" + swaggerUrl + "\" target=\"_blank\" title=\"Swagger Restful API\">" + swaggerUrl + "</a>";

        String druidUrl = "http://" + ip + ":" + serverPort + serverContextPath + "druid";
        String druidLink = "<a href=\"" + druidUrl + "\" target=\"_blank\" title=\"druid数据源监控\">" + druidUrl + "</a>";

        StringBuilder sb = new StringBuilder();
        sb.append("Spring Boot Home!<hr/>");
        sb.append("1、Rest API调测界面: " + swaggerLink + "<hr/>");
        sb.append("2、Druid监控界面: " + druidLink + "<hr/>");
        return sb.toString();
    }

}
