package com.latico.archetype.springboot.common.interceptor;

import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <PRE>
 * 认证拦截器
 * </PRE>
 * @Author: LanDingDong
 * @Date: 2019-04-18 09:05:48
 * @Version: 1.0
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOG.info("接收来自客户端的请求，客户端地址：{}，请求路径：{}", request.getRemoteHost(), request.getRequestURI());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) throws Exception {
        LOG.info("返回响应给客户端，客户端地址：{}，请求路径：{}", httpServletRequest.getRemoteHost(), httpServletRequest.getRequestURI());
    }
}
