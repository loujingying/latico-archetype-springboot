package com.latico.archetype.springboot.convert.impl;

import com.latico.archetype.springboot.bean.bo.DemoTimeParam;
import com.latico.archetype.springboot.convert.DemoConvert;

import java.sql.Timestamp;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-06-07 14:49
 * @Version: 1.0
 */
public class DemoConvertImpl implements DemoConvert {

    @Override
    public DemoTimeParam convert(DemoTimeParam bean, String name) {
        bean.setName("被处理器:" + name + "处理完");
        bean.setTime(new Timestamp(System.currentTimeMillis()));
        return bean;
    }

}
