package com.latico.archetype.springboot.hadoop;

import com.latico.commons.hadoop.HdfsUtils;
import org.apache.hadoop.fs.FileSystem;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class HdfsUtilsTest {


    /**
     *
     */
    @Test
    public void mkdir(){
        FileSystem fileSystem = null;
        try {
            fileSystem = HdfsUtils.newInstanceFileSystem("hdfs://192.168.101.103:9000", "hadoop");
            String dir = "/demo";
            System.out.println("创建目录:" + HdfsUtils.mkdir(fileSystem, dir));
            System.out.println("是否存在目录:" + HdfsUtils.existPath(fileSystem, dir));
            System.out.println("删除目录:" + HdfsUtils.deleteFile(fileSystem, dir));
            System.out.println("是否存在目录:" + HdfsUtils.existPath(fileSystem, dir));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            HdfsUtils.close(fileSystem);
        }
    }

    @Test
    public void test(){
        FileSystem fileSystem = null;
        try {
            fileSystem = HdfsUtils.newInstanceFileSystem("hdfs://192.168.101.103:9000", "hadoop");
            String dir = "/demo";
            System.out.println(HdfsUtils.mkdir(fileSystem, dir));
            System.out.println("是否存在目录:" + HdfsUtils.existPath(fileSystem, dir));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            HdfsUtils.close(fileSystem);
        }
    }

    /**org.apache.hadoop.mapred.Mapper
     *
     */
    @Test
    public void test3(){
        System.out.println(new Date().getTime());
    }
}