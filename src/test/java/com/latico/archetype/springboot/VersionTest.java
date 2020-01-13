package com.latico.archetype.springboot;

import com.latico.commons.common.util.version.VersionUtils;
import org.junit.Test;

public class VersionTest {

    @Test
    public void getVersionInfos() {
        VersionUtils.addVersionInfo("项目名称","1.0", "latico", "2020-01-13", "构建项目");
        System.out.println(VersionUtils.getVersionInfosToMarkdown());
    }
}