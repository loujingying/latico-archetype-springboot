package com.latico.archetype.springboot.hadoop;

import org.apache.hadoop.fs.FileSystem;
import org.junit.Test;

import static org.junit.Assert.*;

public class HdfsUtilsTest {

    FileSystem fileSystem = null;
    /**
     *
     */
    @Test
    public void test(){

        try {
            fileSystem = HdfsUtils.newInstanceFileSystem("hdfs://localhost:9000", "latico");
            System.out.println(HdfsUtils.existPath(fileSystem, "/abc"));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            HdfsUtils.close(fileSystem);
        }
    }
}