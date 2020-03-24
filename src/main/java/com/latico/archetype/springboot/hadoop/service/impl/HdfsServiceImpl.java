package com.latico.archetype.springboot.hadoop.service.impl;

import com.latico.archetype.springboot.common.util.JsonUtils;
import com.latico.archetype.springboot.hadoop.HdfsUtils;
import com.latico.archetype.springboot.hadoop.config.HdfsConfig;
import com.latico.archetype.springboot.hadoop.service.HdfsService;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import com.latico.commons.common.util.string.StringUtils;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;

/**
 * <PRE>
 * 注意，这是原型模式
 * 上层也要单线程操作
 * </PRE>
 *
 * @author: latico
 * @date: 2020-03-23 17:51
 * @version: 1.0
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HdfsServiceImpl implements HdfsService {
    /** 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(HdfsServiceImpl.class);

    @Autowired
    private HdfsConfig hdfsConfig;

    private static final int bufferSize = 1024 * 1024 * 64;

    /**
     * 文件系统对象
     */
    private FileSystem fs;

    @PostConstruct
    public synchronized void initFileSystem() {
        try {
            if (fs != null) {
                HdfsUtils.close(fs);
            }
            fs = HdfsUtils.newInstanceFileSystem(hdfsConfig.getUrl(), hdfsConfig.getUsername());
        } catch (Exception e) {
            LOG.error("", e);
        }
    }
    /**
     * 在HDFS创建文件夹
     * @param path
     * @return
     * @throws Exception
     */
    public boolean mkdir(String path) throws Exception {
        return HdfsUtils.mkdir(fs, path);
    }

    /**
     * 判断HDFS文件是否存在
     * @param path
     * @return
     * @throws Exception
     */
    public boolean existFile(String path) throws Exception {
        return HdfsUtils.existPath(fs, path);
    }

    /**
     * 读取HDFS目录信息
     * @param path
     * @return
     * @throws Exception
     */
    public FileStatus[] readPathInfo(String path) throws Exception {
        return HdfsUtils.readPathInfo(fs, path);
    }

    /**
     * HDFS创建文件
     * @param path
     * @param file
     * @throws Exception
     */
    public void createFile(String path, MultipartFile file) throws Exception {
        HdfsUtils.createFile(fs, path, file);
    }

    /**
     * 读取HDFS文件内容
     * @param path
     * @return
     * @throws Exception
     */
    public String readFile(String path) throws Exception {
        return HdfsUtils.readFileDataToString(fs, path);
    }

    /**
     * 读取HDFS文件列表
     * @param path
     * @return
     * @throws Exception
     */
    public RemoteIterator<LocatedFileStatus> listFile(String path) throws Exception {
        return HdfsUtils.listFile(fs, path);
    }

    /**
     * HDFS重命名文件
     * @param oldName
     * @param newName
     * @return
     * @throws Exception
     */
    public boolean renameFile(String oldName, String newName) throws Exception {
        return HdfsUtils.renameFile(fs, oldName, newName);
    }

    /**
     * 删除HDFS文件
     * @param path
     * @return
     * @throws Exception
     */
    public boolean deleteFile(String path) throws Exception {
        return HdfsUtils.deleteFile(fs, path);
    }

    /**
     * 上传HDFS文件
     * @param path
     * @param uploadPath
     * @throws Exception
     */
    public void uploadFile(String path, String uploadPath) throws Exception {
        HdfsUtils.uploadFile(fs, path, uploadPath);
    }

    /**
     * 下载HDFS文件
     * @param localFilePath
     * @param hdfsFilePath
     * @throws Exception
     */
    public void downloadFile(String localFilePath, String hdfsFilePath) throws Exception {
        HdfsUtils.downloadFile(fs, localFilePath, hdfsFilePath);
    }

    /**
     * HDFS文件复制
     * @param localFilePath
     * @param hdfsFilePath
     * @throws Exception
     */
    public void copyFile(String localFilePath, String hdfsFilePath) throws Exception {
        HdfsUtils.copyFile(fs, localFilePath, hdfsFilePath, bufferSize);
    }

    /**
     * 打开HDFS上的文件并返回byte数组
     * @param path
     * @return
     * @throws Exception
     */
    public byte[] readFileDataToBytes(String path) throws Exception {
        return HdfsUtils.readFileDataToBytes(fs, path);
    }

    /**
     * 打开HDFS上的文件并返回java对象
     * @param path
     * @param clazz
     * @return
     * @throws Exception
     * @param <T>
     */
    public <T extends Object> T readFileDataToObject(String path, Class<T> clazz) throws Exception {
        return HdfsUtils.readFileDataToObject(fs, path, clazz);
    }

    /**
     * 获取某个文件在HDFS的集群位置
     * @param path
     * @return
     * @throws Exception
     */
    public BlockLocation[] getFileBlockLocations(String path) throws Exception {
        return HdfsUtils.getFileBlockLocations(fs, path);
    }
}
