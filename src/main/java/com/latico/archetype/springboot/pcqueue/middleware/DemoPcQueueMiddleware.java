package com.latico.archetype.springboot.pcqueue.middleware;

import com.latico.archetype.springboot.bean.bo.DemoTimeParam;
import com.latico.archetype.springboot.pcqueue.customer.DemoCustomerEventHandler1Impl;
import com.latico.archetype.springboot.pcqueue.customer.DemoCustomerEventHandler2Impl;
import com.latico.archetype.springboot.pcqueue.customer.DemoCustomerEventHandler3Impl;
import com.latico.commons.disruptor.DisruptorUtils;
import com.latico.commons.disruptor.pc.impl.EventProducerDefault;
import com.latico.commons.disruptor.pc.impl.ExceptionHandlerDefaultImpl;
import com.latico.commons.disruptor.pc.impl.OneArgEventDefault;
import com.lmax.disruptor.dsl.Disruptor;

/**
 * <PRE>
 * 中间件
 * </PRE>
 *
 * @Author: LanDingDong
 * @Date: 2019-06-07 1:09
 * @Version: 1.0
 */
public class DemoPcQueueMiddleware {
    /**
     * 并发器的队列大小,必须是2的N次方,这里只是示例，使用了16
     */
    private int ringBufferSize = 16;

    /**
     * 并发器Disruptor
     */
    private Disruptor<OneArgEventDefault<DemoTimeParam>> disruptor;

    /**
     这里添加3个处理器，一般情况是添加一个就可以了，这里为了演示效果，使用了3个处理器，使用了默认方式创建，
     * 创建的是顺序执行的模式，3个处理都会对一个任务进行处理，一个处理完会把结果传递给下一个，
     * Disruptor还有其他更强的模式，这里演示的是顺序模式
     */
    public DemoPcQueueMiddleware() {
        disruptor = DisruptorUtils.createDisruptorDefault(ringBufferSize, "disruptor",
                        new ExceptionHandlerDefaultImpl<DemoTimeParam>(),
                        new DemoCustomerEventHandler1Impl(),
                        new DemoCustomerEventHandler2Impl(),
                        new DemoCustomerEventHandler3Impl());
    }

    /**
     * 获取一个生产者
     * @return
     */
    public EventProducerDefault<DemoTimeParam> getProducer() {
        return DisruptorUtils.createProducerDefault(disruptor);
    }


    /**
     * 关闭并发器
     */
    public void close() {
        disruptor.shutdown();
    }
}
