package com.latico.archetype.springboot.service.impl;

import com.latico.archetype.springboot.bean.bo.DemoTimeParam;
import com.latico.archetype.springboot.common.util.DateTimeUtils;
import com.latico.archetype.springboot.common.util.MD5Utils;
import com.latico.archetype.springboot.config.xml.XmlBizConfig;
import com.latico.archetype.springboot.dao.primary.entity.Demo;
import com.latico.archetype.springboot.dao.primary.mapper.DemoMapper;
import com.latico.archetype.springboot.dao.primary.repository.DemoRepository;
import com.latico.archetype.springboot.dao.secondary.mapper.Demo2Mapper;
import com.latico.archetype.springboot.dao.secondary.repository.Demo2Repository;
import com.latico.archetype.springboot.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
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
@SuppressWarnings("ALL")
@Service
public class DemoServiceImpl implements DemoService {

    /**
     * 日志
     */
    private final static Logger LOG = LoggerFactory.getLogger(DemoServiceImpl.class);
    /**
     * 测试JPA方式
     */
    @Autowired
    DemoMapper demoMapper;

    @Autowired
    DemoRepository demoRepository;

    /**
     * 测试Mybatis方式
     */
    @Autowired
    Demo2Mapper demo2Mapper;

    @Autowired
    Demo2Repository demo2Repository;
    @Override
    public String serverTimeStr() {
        DemoTimeParam time = new DemoTimeParam();
        time.setTime(new Timestamp(System.currentTimeMillis()));

        String str = "机组ID：" + XmlBizConfig.getInstance().getId() + "，服务器当前时间：" + time.toString();
        LOG.info("字符串类型完成请求处理,结果:{}", str);
        return str;
    }

    @Override
    public DemoTimeParam serverTime() {
        DemoTimeParam time = new DemoTimeParam();
        time.setTime(new Timestamp(System.currentTimeMillis()));

        String str = "机组ID：" + XmlBizConfig.getInstance().getId() + "，服务器当前时间：" + time.toString();
        LOG.info("对象类型完成请求处理,结果:" + str);
        return time;
    }

    @Override
    public List<Demo> insertAndSelectDemo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        insert(httpServletRequest);
        insertDemo(httpServletRequest);
        List<Demo> all = demoRepository.findAll();

        return all;
    }

    @Override
    public List<com.latico.archetype.springboot.dao.secondary.entity.Demo> selectDemo2(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return demo2Mapper.findAll();
    }

    @Override
    public String queryAllDemo() {
        LOG.info("demoMapper 查询结果：\r\n{}", demoMapper.findAll());
        LOG.info("demoRepository 查询结果：\n{}", demoRepository.findAll());
        LOG.info("demo2Mapper 查询结果：\n{}", demo2Mapper.findAll());
        LOG.info("demo2Repository 查询结果：\n{}", demo2Repository.findAll());

        return "查询成功";
    }

    @Transactional
    protected void insert(HttpServletRequest httpServletRequest) {
        Demo demo = new Demo();
        Integer taskIdMax = demoRepository.getTaskIdMax();
        demo.setTaskId(++taskIdMax);
        demo.setExecType(httpServletRequest.getRequestURI());
        demo.setExecStatus(2);
        demo.setStatusDescr("成功");
        demo.setCallClientId(httpServletRequest.getRemoteHost());
        demo.setCreateTime(DateTimeUtils.getSysDate());
        demo.setUpdateTime(demo.getCreateTime());
        demo.setId(MD5Utils.toLowerCaseMd5(demo.getTaskId()));

        LOG.info("准备插入:{}", demo);
        Demo save = demoRepository.saveAndFlush(demo);
        LOG.info("插入成功:{}", save);
    }

    @Transactional
    protected void insertDemo(HttpServletRequest httpServletRequest) {
        List<Demo> list = new ArrayList<>();
        Integer taskIdMax = demoRepository.getTaskIdMax();
        Demo demo = new Demo();
        list.add(demo);
        demo.setTaskId(++taskIdMax);
        demo.setExecStatus(2);
        demo.setExecType(httpServletRequest.getRequestURI());
        demo.setStatusDescr("成功");
        demo.setCallClientId(httpServletRequest.getRemoteHost());
        demo.setCreateTime(DateTimeUtils.getSysDate());
        demo.setUpdateTime(demo.getCreateTime());
        demo.setId(MD5Utils.toLowerCaseMd5(demo.getTaskId()));
        demo = new Demo();
        list.add(demo);
        demo.setTaskId(++taskIdMax);
        demo.setExecStatus(2);
        demo.setExecType(httpServletRequest.getRequestURI());
        demo.setStatusDescr("成功");
        demo.setCallClientId(httpServletRequest.getRemoteHost());
        demo.setCreateTime(DateTimeUtils.getSysDate());
        demo.setUpdateTime(demo.getCreateTime());
        demo.setId(MD5Utils.toLowerCaseMd5(demo.getTaskId()));

        demoRepository.insertBatch(list, 500);

        List<Demo> demos = demoRepository.updateBatch(list, 500);
        LOG.info("批量更新:{}", demos);
    }

}
