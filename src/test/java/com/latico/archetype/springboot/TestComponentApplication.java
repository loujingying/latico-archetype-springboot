package com.latico.archetype.springboot;

import com.latico.archetype.springboot.config.ConfigTestApplication;
import com.latico.archetype.springboot.controller.HomeController;
import com.latico.archetype.springboot.dao.DaoTestApplication;
import com.latico.archetype.springboot.service.ServiceTestApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <PRE>
 * 指定扫描的标准组件的目录包括：dao、controller、config、service，如果有需要，请自行添加
 * </PRE>
 *
 * @Author: LanDingDong
 * @Date: 2019-06-27 10:58
 * @Version: 1.0
 */
@SpringBootApplication(scanBasePackageClasses = {HomeController.class, DaoTestApplication.class, ConfigTestApplication.class, ServiceTestApplication.class})
public class TestComponentApplication {
}
