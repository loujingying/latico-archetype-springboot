package com.latico.archetype.springboot.pcqueue.customer;

import com.latico.archetype.springboot.bean.bo.DemoTimeParam;
import com.latico.archetype.springboot.convert.DemoConvert;
import com.latico.archetype.springboot.convert.impl.DemoConvertImpl;
import com.latico.archetype.springboot.dao.secondary.mapper.Demo2Mapper;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import com.latico.commons.disruptor.pc.impl.OneArgEventDefault;
import com.latico.commons.spring.util.SpringUtils;
import com.lmax.disruptor.EventHandler;

/**
 * <PRE>
 *  消费者
 *
 * </PRE>
 * @Author: LanDingDong
 * @Date: 2019-06-06 18:55:35
 * @Version: 1.0
 */
public class DemoCustomerEventHandler1Impl implements EventHandler<OneArgEventDefault<DemoTimeParam>> {
    private static final Logger LOG = LoggerFactory.getLogger(DemoCustomerEventHandler1Impl.class);
    /**
     * 本处理器的名字
     */
    private String name = "1";
    /**
     * 转换处理器
     */
    private DemoConvert demoConvert = new DemoConvertImpl();

    /**
     * 假如要把数据入库，因为本类没有使用spring容器管理，所以不能使用注解方式获取spring bean，要通过下面方式
     */
    private Demo2Mapper demo2Mapper = SpringUtils.getBean(Demo2Mapper.class);

    public DemoCustomerEventHandler1Impl() {
    }
    public DemoCustomerEventHandler1Impl(String name) {
        this.name = name;
    }
    @Override
    public void onEvent(OneArgEventDefault<DemoTimeParam> event, long sequence, boolean endOfBatch) throws Exception {
        LOG.info("线程:{},处理器:{},事件序号:{}, 是否批量事件的结尾:{}, 事件内容:{}", Thread.currentThread().getName(), name, sequence, endOfBatch, event);

//        转换处理器
        DemoTimeParam convertResult = demoConvert.convert(event.getData(), name);
        event.setData(convertResult);
    }
}