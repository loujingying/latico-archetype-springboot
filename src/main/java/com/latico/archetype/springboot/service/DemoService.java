package com.latico.archetype.springboot.service;

import com.latico.archetype.springboot.bean.bo.DemoTimeParam;
import com.latico.archetype.springboot.dao.secondary.entity.Demo2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <PRE>
 *
 * </PRE>
 * <B>项	       目：</B>
 * <B>技术支持：</B>
 *
 * @author <B><a href="mailto:latico@qq.com"> latico </a></B>
 * @version <B>V1.0 2018年10月11日</B>
 * @since <B>JDK1.6</B>
 */
public interface DemoService {

    /**
     * @return 字符串类型
     */
    String serverTimeStr();

    /**
     * @return 对象类型
     */
    DemoTimeParam serverTime();

    List<com.latico.archetype.springboot.dao.primary.entity.Demo> insertAndSelectDemo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);

    List<Demo2> selectDemo2(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);

    String queryAllDemo();
}
