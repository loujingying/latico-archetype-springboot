package com.latico.archetype.springboot;


import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import com.latico.commons.common.util.version.VersionUtils;

/**
 * <PRE>
 * 记录程序的版本信息
 *
 * 把版本记录通过 addVersionInfo写进 VersionUtils 中，然后通过 VersionUtils.getVersionInfosToMarkdown()读取
 * </PRE>
 *
 * @author: latico
 * @date: 2020-01-13 10:07
 * @version: 1.0
 */
public class Version {

    private static final Logger LOG = LoggerFactory.getLogger(Version.class);
    /**
     * 初始化版本信息
     */
    public static void init() {
        //初始化这里什么也不做，目的是为了初始化同步代码块
        System.out.println("初始化版本信息");
        LOG.info("初始化版本信息");
    }

    /**
     * 项目名称
     */
    private static final String PROJECT_NAME = "latico-archetype-springboot";

    //为了实现只加载一次，这里使用静态代码块
    static {
        VersionUtils.addVersionInfo(PROJECT_NAME,"1.0", "latico", "2020-01-13",
                "构建项目");

        VersionUtils.addVersionInfo(PROJECT_NAME,"1.0", "latico", "2020-04-08",
                "集成postgreSql");
    }

}
