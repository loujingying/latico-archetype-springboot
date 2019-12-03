package com.latico.archetype.springboot.service.impl;

import com.latico.archetype.springboot.bean.bo.DemoByPageResult;
import com.latico.archetype.springboot.bean.bo.DemoTimeParam;
import com.latico.archetype.springboot.common.util.DateTimeUtils;
import com.latico.archetype.springboot.common.util.MD5Utils;
import com.latico.archetype.springboot.common.util.PageHelperUtils;
import com.latico.archetype.springboot.common.util.PageableUtils;
import com.latico.archetype.springboot.config.xml.XmlBizConfig;
import com.latico.archetype.springboot.dao.primary.jdbctemplate.DemoJdbcTemplate;
import com.latico.archetype.springboot.dao.primary.entity.Demo;
import com.latico.archetype.springboot.dao.primary.mapper.DemoMapper;
import com.latico.archetype.springboot.dao.primary.repository.DemoRepository;
import com.latico.archetype.springboot.dao.secondary.jdbctemplate.Demo2JdbcTemplate;
import com.latico.archetype.springboot.dao.secondary.entity.Demo2;
import com.latico.archetype.springboot.dao.secondary.mapper.Demo2Mapper;
import com.latico.archetype.springboot.dao.secondary.repository.Demo2Repository;
import com.latico.archetype.springboot.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Autowired
    DemoJdbcTemplate demoJdbcTemplate;
    @Autowired
    Demo2JdbcTemplate demo2JdbcTemplate;

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
    public List<Demo2> selectDemo2(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return demo2Mapper.findAll();
    }

    @Override
    public String queryAllDemo() {
        PageHelperUtils.startPage(1, 5);
        LOG.info("demoMapper 查询结果：\r\n{}", demoMapper.findAll());
        Pageable pageable = PageableUtils.createPageable(1, 5);
        LOG.info("demoRepository 查询结果：\n{}", demoRepository.findAll(pageable));
        LOG.info("demoJdbcTemplate 查询结果：\n{}", demoJdbcTemplate.queryAll());

        PageHelperUtils.startPage(1, 5);
        LOG.info("demo2Mapper 查询结果：\n{}", demo2Mapper.findAll());
        LOG.info("demo2Repository 查询结果：\n{}", demo2Repository.findAll(pageable));
        LOG.info("demo2JdbcTemplate 查询结果：\n{}", demo2JdbcTemplate.queryAll());

        return "查询成功";
    }

    @Override
    public DemoByPageResult queryDemoByPage(int pageNum, int pageSize, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String sortField = "autoId";

        Pageable pageable = PageableUtils.createPageable(pageNum, pageSize, Sort.Direction.ASC, sortField);
        Page<Demo> page = demoRepository.findAll(pageable);
        DemoByPageResult demoByPage = new DemoByPageResult();
        demoByPage.setDemos(page.getContent());
        demoByPage.setPageNum(pageNum);
        demoByPage.setPageSize(pageSize);
        demoByPage.setTotalPages(page.getTotalPages());
        demoByPage.setTotalElements(page.getTotalElements());

        return demoByPage;
    }

    @Transactional
    protected void insert(HttpServletRequest httpServletRequest) {
        Demo demo = new Demo();
        Integer taskIdMax = demoRepository.getTaskIdMax();
        if (taskIdMax == null) {
            taskIdMax = 1;
        }
        demo.setTaskId(++taskIdMax);
        demo.setExecType(httpServletRequest.getRequestURI());
        demo.setExecStatus(2);
        demo.setStatusDescr("成功");
        demo.setCallClientId(httpServletRequest.getRemoteHost());
        demo.setCreateTime(DateTimeUtils.getSysDate());
        demo.setUpdateTime(demo.getCreateTime());
        demo.setId(MD5Utils.toLowerCaseMd5(demo.getTaskId()));
        demo.setHandleStatus(0);

        LOG.info("准备插入:{}", demo);
        Demo save = demoRepository.saveAndFlush(demo);
        LOG.info("插入成功:{}", save);
    }

    @Transactional
    protected void insertDemo(HttpServletRequest httpServletRequest) {
        List<Demo> list = new ArrayList<>();
        Integer taskIdMax = demoRepository.getTaskIdMax();
        if (taskIdMax == null) {
            taskIdMax = 1;
        }
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
        demo.setHandleStatus(0);
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
        demo.setHandleStatus(0);

        demoRepository.insertBatch(list, 500);

        List<Demo> demos = demoRepository.updateBatch(list, 500);
        LOG.info("批量更新:{}", demos);
    }

}
