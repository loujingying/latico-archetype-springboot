package com.latico.archetype.springboot;

import org.junit.Test;

public class VersionTest {

    @Test
    public void getVersionInfos() {
        Version version = new Version();
        System.out.println(version.getVersionInfosByMarkdown());
    }
}