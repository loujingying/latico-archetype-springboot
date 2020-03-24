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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void initFileSystem() {
        try {
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
        return HdfsUtils.existFile(fs, path);
    }

    /**
     * 读取HDFS目录信息
     * @param path
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> readPathInfo(String path) throws Exception {
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
        if (StringUtils.isEmpty(path)) {
            return null;
        }
        if (!existFile(path)) {
            return null;
        }
        
        // 目标路径
        Path srcPath = new Path(path);
        FSDataInputStream inputStream = null;
        try {
            inputStream = fs.open(srcPath);
            // 防止中文乱码
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String lineTxt = "";
            StringBuilder sb = new StringBuilder();
            while ((lineTxt = reader.readLine()) != null) {
                sb.append(lineTxt);
            }
            return sb.toString();
        } finally {
            com.latico.commons.common.util.io.IOUtils.close(inputStream);
            
        }
    }

    /**
     * 读取HDFS文件列表
     * @param path
     * @return
     * @throws Exception
     */
    public List<Map<String, String>> listFile(String path) throws Exception {
        if (StringUtils.isEmpty(path)) {
            return null;
        }
        if (!existFile(path)) {
            return null;
        }

        
        // 目标路径
        Path srcPath = new Path(path);
        // 递归找到所有文件
        RemoteIterator<LocatedFileStatus> filesList = fs.listFiles(srcPath, true);
        List<Map<String, String>> returnList = new ArrayList<>();
        while (filesList.hasNext()) {
            LocatedFileStatus next = filesList.next();
            String fileName = next.getPath().getName();
            Path filePath = next.getPath();
            Map<String, String> map = new HashMap<>();
            map.put("fileName", fileName);
            map.put("filePath", filePath.toString());
            returnList.add(map);
        }
        
        return returnList;
    }

    /**
     * HDFS重命名文件
     * @param oldName
     * @param newName
     * @return
     * @throws Exception
     */
    public boolean renameFile(String oldName, String newName) throws Exception {
        if (StringUtils.isEmpty(oldName) || StringUtils.isEmpty(newName)) {
            return false;
        }
        
        // 原文件目标路径
        Path oldPath = new Path(oldName);
        // 重命名目标路径
        Path newPath = new Path(newName);
        boolean isOk = fs.rename(oldPath, newPath);
        
        return isOk;
    }

    /**
     * 删除HDFS文件
     * @param path
     * @return
     * @throws Exception
     */
    public boolean deleteFile(String path) throws Exception {
        if (StringUtils.isEmpty(path)) {
            return false;
        }
        if (!existFile(path)) {
            return false;
        }
        
        Path srcPath = new Path(path);
        boolean isOk = fs.deleteOnExit(srcPath);
        
        return isOk;
    }

    /**
     * 上传HDFS文件
     * @param path
     * @param uploadPath
     * @throws Exception
     */
    public void uploadFile(String path, String uploadPath) throws Exception {
        if (StringUtils.isEmpty(path) || StringUtils.isEmpty(uploadPath)) {
            return;
        }
        
        // 上传路径
        Path clientPath = new Path(path);
        // 目标路径
        Path serverPath = new Path(uploadPath);

        // 调用文件系统的文件复制方法，第一个参数是否删除原文件true为删除，默认为false
        fs.copyFromLocalFile(false, clientPath, serverPath);
        
    }

    /**
     * 下载HDFS文件
     * @param path
     * @param downloadPath
     * @throws Exception
     */
    public void downloadFile(String path, String downloadPath) throws Exception {
        if (StringUtils.isEmpty(path) || StringUtils.isEmpty(downloadPath)) {
            return;
        }
        
        // 上传路径
        Path clientPath = new Path(path);
        // 目标路径
        Path serverPath = new Path(downloadPath);

        // 调用文件系统的文件复制方法，第一个参数是否删除原文件true为删除，默认为false
        fs.copyToLocalFile(false, clientPath, serverPath);
        
    }

    /**
     * HDFS文件复制
     * @param sourcePath
     * @param targetPath
     * @throws Exception
     */
    public void copyFile(String sourcePath, String targetPath) throws Exception {
        if (StringUtils.isEmpty(sourcePath) || StringUtils.isEmpty(targetPath)) {
            return;
        }
        
        // 原始文件路径
        Path oldPath = new Path(sourcePath);
        // 目标路径
        Path newPath = new Path(targetPath);

        FSDataInputStream inputStream = null;
        FSDataOutputStream outputStream = null;
        try {
            inputStream = fs.open(oldPath);
            outputStream = fs.create(newPath);

            IOUtils.copyBytes(inputStream, outputStream, bufferSize, false);
        } finally {
            com.latico.commons.common.util.io.IOUtils.close(inputStream);
            com.latico.commons.common.util.io.IOUtils.close(outputStream);
        }
    }

    /**
     * 打开HDFS上的文件并返回byte数组
     * @param path
     * @return
     * @throws Exception
     */
    public byte[] openFileToBytes(String path) throws Exception {
        if (StringUtils.isEmpty(path)) {
            return null;
        }
        if (!existFile(path)) {
            return null;
        }
        
        // 目标路径
        Path srcPath = new Path(path);
        try {
            FSDataInputStream inputStream = fs.open(srcPath);
            return IOUtils.readFullyToByteArray(inputStream);
        } finally {
            
        }
    }

    /**
     * 打开HDFS上的文件并返回java对象
     * @param path
     * @return
     * @throws Exception
     */
    public <T extends Object> T openFileToObject(String path, Class<T> clazz) throws Exception {
        if (StringUtils.isEmpty(path)) {
            return null;
        }
        if (!existFile(path)) {
            return null;
        }
        String jsonStr = readFile(path);
        return JsonUtils.jsonToObj(	jsonStr, clazz);
    }

    /**
     * 获取某个文件在HDFS的集群位置
     * @param path
     * @return
     * @throws Exception
     */
    public BlockLocation[] getFileBlockLocations(String path) throws Exception {
        if (StringUtils.isEmpty(path)) {
            return null;
        }
        if (!existFile(path)) {
            return null;
        }
        
        // 目标路径
        Path srcPath = new Path(path);
        FileStatus fileStatus = fs.getFileStatus(srcPath);
        return fs.getFileBlockLocations(fileStatus, 0, fileStatus.getLen());
    }
}
