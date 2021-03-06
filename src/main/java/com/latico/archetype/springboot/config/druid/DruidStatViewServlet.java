package com.latico.archetype.springboot.config.druid;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * <PRE>
 *  配置登录账号的白名单
 *  访问：http://127.0.0.1:8080/demo/druid
 * </PRE>
 * @author: latico
 * @date: 2019-02-27 11:23:57
 * @version: 1.0
 */
@WebServlet(urlPatterns = "/druid/*",
        initParams={
                @WebInitParam(name="allow",value=""),// IP白名单 (没有配置或者为空，则允许所有访问)
                @WebInitParam(name="deny",value=""),// IP黑名单 (存在共同时，deny优先于allow)
                @WebInitParam(name="loginUsername",value="admin"),// 用户名
                @WebInitParam(name="loginPassword",value="admin"),// 密码
                @WebInitParam(name="resetEnable",value="false")// 禁用HTML页面上的“Reset All”功能
        })
public class DruidStatViewServlet extends StatViewServlet {

    private static final long serialVersionUID = 1L;
}