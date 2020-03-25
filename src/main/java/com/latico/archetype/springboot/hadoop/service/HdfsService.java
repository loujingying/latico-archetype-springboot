package com.latico.archetype.springboot.hadoop.service;

import com.latico.commons.hadoop.HdfsUtils;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.RemoteIterator;
import org.springframework.web.multipart.MultipartFile;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @author: latico
 * @date: 2020-03-23 17:50
 * @version: 1.0
 */
public interface HdfsService {
    /**
     * 在HDFS创建文件夹
     * @param path
     * @return
     * @throws Exception
     */
    public boolean mkdir(String path) throws Exception;

    /**
     * 判断HDFS文件是否存在
     * @param path
     * @return
     * @throws Exception
     */
    public boolean existFile(String path) throws Exception;

    /**
     * 读取HDFS目录信息
     * @param path
     * @return
     * @throws Exception
     */
    public FileStatus[] readPathInfo(String path) throws Exception;

    /**
     * HDFS创建文件
     * @param path
     * @param file
     * @throws Exception
     */
    public void createFile(String path, MultipartFile file) throws Exception;

    /**
     * @param path
     * @param fileName
     * @param data
     * @throws Exception
     */
    public void createFile(String path, String fileName, byte[] data) throws Exception;

    /**
     * 读取HDFS文件内容
     * @param path
     * @return
     * @throws Exception
     */
    public String readFile(String path) throws Exception;

    /**
     * 读取HDFS文件列表
     * @param path
     * @return
     * @throws Exception
     */
    public RemoteIterator<LocatedFileStatus> listFile(String path) throws Exception;

    /**
     * HDFS重命名文件
     * @param oldName
     * @param newName
     * @return
     * @throws Exception
     */
    public boolean renameFile(String oldName, String newName) throws Exception;

    /**
     * 删除HDFS文件
     * @param path
     * @return
     * @throws Exception
     */
    public boolean deleteFile(String path) throws Exception;

    /**
     * 上传HDFS文件
     * @param path
     * @param uploadPath
     * @throws Exception
     */
    public void uploadFile(String path, String uploadPath) throws Exception;

    /**
     * 下载HDFS文件
     * @param localFilePath
     * @param hdfsFilePath
     * @throws Exception
     */
    public void downloadFile(String localFilePath, String hdfsFilePath) throws Exception;

    /**
     * HDFS文件复制
     * @param localFilePath
     * @param hdfsFilePath
     * @throws Exception
     */
    public void copyFile(String localFilePath, String hdfsFilePath) throws Exception;

    /**
     * 打开HDFS上的文件并返回byte数组
     * @param path
     * @return
     * @throws Exception
     */
    public byte[] readFileDataToBytes(String path) throws Exception;

    /**
     * 打开HDFS上的文件并返回java对象
     * @param path
     * @param clazz
     * @return
     * @throws Exception
     * @param <T>
     */
    public <T extends Object> T readFileDataToObject(String path, Class<T> clazz) throws Exception;

    /**
     * 获取某个文件在HDFS的集群位置
     * @param path
     * @return
     * @throws Exception
     */
    public BlockLocation[] getFileBlockLocations(String path) throws Exception;
}
