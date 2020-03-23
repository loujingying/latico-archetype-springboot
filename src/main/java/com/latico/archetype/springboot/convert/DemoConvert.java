package com.latico.archetype.springboot.convert;

import com.latico.archetype.springboot.bean.bo.DemoTimeParam;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @author: latico
 * @date: 2019-06-07 14:52
 * @version: 1.0
 */
public interface DemoConvert {

    /**
     * 转换
     * @param bean
     * @param name
     * @return
     */
    public DemoTimeParam convert(DemoTimeParam bean, String name);
}
