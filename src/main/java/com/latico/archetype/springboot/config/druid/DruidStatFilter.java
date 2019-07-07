package com.latico.archetype.springboot.config.druid;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * <PRE>
 *  配置druid过滤规则：监控所有的页面，排除以下配置的文件
 * </PRE>
 * @Author: latico
 * @Date: 2019-02-27 11:24:12
 * @Version: 1.0
 */
@WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",
        initParams={
                @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源
        })
public class DruidStatFilter extends WebStatFilter {
}