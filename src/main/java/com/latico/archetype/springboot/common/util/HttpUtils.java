package com.latico.archetype.springboot.common.util;

import com.latico.commons.common.util.io.IOUtils;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * <PRE>
 * HTTP操作工具
 * </PRE>
 *
 * @author: latico
 * @date: 2020-03-27 16:44
 * @version: 1.0
 */
public class HttpUtils {
    /** 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * 下载文件
     * 客户端请求服务端，这个方法是发送文件到客户端
     * @param response
     * @param localFilePath 被下载的文件在服务器中的路径
     */
    public static boolean downloadFile(HttpServletResponse response, String localFilePath) {
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {

            File file = new File(localFilePath);
            if (file.exists()) {
                // 设置强制下载不打开
                response.setContentType("application/force-download");
                response.addHeader("Content-Disposition", "attachment;fileName=" + file.getName());
                byte[] buffer = new byte[1024];

                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream outputStream = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                outputStream.flush();
            }
            return true;
        } catch (Exception e) {
            LOG.error("", e);
        } finally {
            IOUtils.close(bis);
            IOUtils.close(fis);
        }
        return false;
    }
}
