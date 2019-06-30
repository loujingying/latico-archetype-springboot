package com.latico.archetype.springboot.service;

import com.latico.archetype.springboot.bean.bo.DemoTimeParam;

/**
 * <PRE>
 *
 * </PRE>
 * <B>项	       目：</B>研发中心公共-综合网管
 * <B>技术支持：</B>凯通科技股份有限公司 (c)
 *
 * @author <B><a href="mailto:landingdong@gdcattsoft.com"> 蓝鼎栋 </a></B>
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

}
