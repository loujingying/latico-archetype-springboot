package com.latico.archetype.springboot.timerjob;

import com.latico.archetype.springboot.bean.bo.DemoTimeParam;
import com.latico.archetype.springboot.pcqueue.middleware.DemoPcQueueMiddleware;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import com.latico.commons.disruptor.pc.impl.EventProducerDefault;
import com.latico.commons.timer.quartz.QuartzManager;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.sql.Timestamp;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <PRE>
 * 定时调度的实际工作者JOB，使用示例
 * 因为实现{@link Job}的实例，每次执行都会创建一个新的对象，所以不应该在该类里面写对象字段变量，一般用静态变量或者引用外界对象；
 * 该示例中，我们创建了一个生产者消费者并发器，然后执行任务的时候，就把任务生产到并发器中，消费者就会执行
 * </PRE>
 * <B>项	       目：</B>研发中心公共-综合网管
 * <B>技术支持：</B>凯通科技股份有限公司 (c)
 *
 * @author <B><a href="mailto:landingdong@gdcattsoft.com"> 蓝鼎栋 </a></B>
 * @version <B>V1.0 2018年11月7日</B>
 * @since <B>JDK1.6</B>
 */
public class DemoQuartzJob implements Job {

    private static final Logger LOG = LoggerFactory.getLogger(DemoQuartzJob.class);

    /**
     * 作业的名称
     */
    public static final String JOB_NAME = "QUARTZ_JOB_NAME";

    /**
     * 触发器名称
     */
    public static final String TRIGGER_NAME = "QUARTZ_TRIGGER_NAME";

    /**
     * 工作组的名称
     */
    public static final String JOB_GROUP_NAME = "QUARTZ_JOB_GROUP";

    /**
     * 触发器组的名称
     */
    public static final String TRIGGER_GROUP_NAME = "QUARTZ_JOB_GROUP";

    /**
     * 设置最多执行几次，因为Job对象会被重建，所以使用静态对象来保持
     */
    private static final AtomicInteger count = new AtomicInteger(2);
    /**
     * 判断是否已经关闭，因为Job对象会被重建，所以使用静态对象来保持
     */
    private static final AtomicBoolean close = new AtomicBoolean(false);

    /**
     * 创建一个中间件，因为Job对象会被重建，所以使用静态对象来保持
     */
    private static final DemoPcQueueMiddleware DEMO_PC_QUEUE_MIDDLEWARE = new DemoPcQueueMiddleware();
    /**
     * 通过中间件拿到一个生产者，因为Job对象会被重建，所以使用静态对象来保持
     */
    private static final EventProducerDefault<DemoTimeParam> producer = DEMO_PC_QUEUE_MIDDLEWARE.getProducer();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

//        次数执行完成后，不再生产数据,但是该调度器仍然会执行空
        if (count.get() <= 0) {
            if (!close.get()) {

//                移除定时器工作任务
                QuartzManager.getInstance().removeJob(JOB_NAME);

//                关闭并发器
                DEMO_PC_QUEUE_MIDDLEWARE.close();

                close.set(true);
            }

        } else {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            LOG.info("======{} 定时任务执行======", timestamp);

            //            生产者生产数据
            DemoTimeParam obj = new DemoTimeParam();
            obj.setTime(timestamp);
            producer.publishEvent(obj);

//            统计加1
            count.decrementAndGet();
            LOG.info("====== 定时任务执行完成======");
        }


    }

}