package com.latico.archetype.springboot.config;

import com.latico.archetype.springboot.common.interceptor.AuthorizationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <PRE>
 *  可以添加拦截器等
 *
 * </PRE>
 * @Author: latico
 * @Date: 2019-06-06 15:01:17
 * @Version: 1.0
 */
@Configuration
public class WebMvcConfigurerImpl implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有
        registry.addInterceptor(new AuthorizationInterceptor())
                .addPathPatterns("/**");
    }

}
