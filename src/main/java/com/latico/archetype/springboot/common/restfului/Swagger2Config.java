package com.latico.archetype.springboot.common.restfului;

import com.latico.archetype.springboot.Application;
import com.latico.archetype.springboot.common.util.CollectionUtils;
import com.latico.commons.common.util.system.SystemUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * <PRE>
 * Swagger2 配置
 * 1、restful的扫描基本包目录
 * 2、网页介绍信息
 * </PRE>
 *
 * @author: latico
 * @date: 2019-02-25 17:14:23
 * @version: 1.0
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * 是否开启swagger
     */
    @Value("${swagger.show}")
    private boolean swaggerShow;

    /**
     * 服务端口
     */
    @Value("${server.port}")
    private String serverPort;

    /**
     * 服务上下文
     */
    @Value("${server.servlet.context-path}")
    private String serverContextPath;

    @Bean
    public Docket createRestApi() {
        // 要扫描的基本包，默认是该类的包名去掉.common.restfului，也就是项目包的根路径，目前直接优化成Application类路径下
//        String basePackage = this.getClass().getPackage().getName().replace(".common.restfului", "");
        String basePackage = Application.class.getPackage().getName();


        //添加扫描基本包目录
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerShow)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * @return 网页介绍信息
     */
    private ApiInfo apiInfo() {
        String serverContextPath = this.serverContextPath;
        final String slashStr = "/";
        if (!serverContextPath.startsWith(slashStr)) {
            serverContextPath = slashStr + serverContextPath;
        }
        if (!serverContextPath.endsWith(slashStr)) {
            serverContextPath = serverContextPath + slashStr;
        }
        String ip = "localhost";
        List<InetAddress> allPhysicsInetAddress = SystemUtils.getAllPhysicsInetAddress();
        if (CollectionUtils.isNotEmpty(allPhysicsInetAddress)) {
            InetAddress inetAddress = allPhysicsInetAddress.get(0);
            ip = inetAddress.getHostAddress();
        }
        String url = "http://" + ip + ":" + serverPort + serverContextPath + "swagger-ui.html";
        String link = "<a href=\"" + url + "\" target=\"_blank\" title=\"Swagger Restful API\">" + url + "</a>";
        return new ApiInfoBuilder()
                .title("Swagger Restful API")
                .description("REST接口调测请访问:" + link)
                .termsOfServiceUrl("http://" + ip + ":" + serverPort + serverContextPath + "swagger-ui.html")
                .contact(new Contact("latico", "", "latico@qq.com"))
                .version("1.0")
                .build();
    }
}
