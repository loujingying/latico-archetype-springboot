package com.latico.archetype.springboot;


import com.latico.commons.common.util.version.VersionUtils;

/**
 * <PRE>
 * 记录程序的版本信息
 *
 * 把版本记录通过 addVersionInfo写进 VersionUtils 中，然后通过 VersionUtils.getVersionInfosToMarkdown()读取
 * </PRE>
 *
 * @Author: latico
 * @Date: 2020-01-13 10:07
 * @Version: 1.0
 */
public class Version {

    static {
        VersionUtils.addVersionInfo("项目名称","1.0", "latico", "2020-01-13", "构建项目");
    }

}
