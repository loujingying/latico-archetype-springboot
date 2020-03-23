package com.latico.archetype.springboot.common.exception;

import com.latico.archetype.springboot.bean.dto.RestResponseDTO;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <PRE>
 * 全局异常拦截器
 * </PRE>
 * @author: latico
 * @date: 2019-04-18 09:06:05
 * @version: 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 全局异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RestResponseDTO handleException(Exception e) {
        RestResponseDTO result = new RestResponseDTO();
        result.setStatus(false);
        result.setName("服务器异常！");
        result.setDescr(e.getMessage());
        LOG.error(e);
        return result;
    }

    /**
     * 自定义示例异常
     * @param e
     * @return
     */
    @ExceptionHandler(DemoException.class)
    @ResponseBody
    public RestResponseDTO handleDemoException(DemoException e) {
        RestResponseDTO result = new RestResponseDTO();
        result.setStatus(false);
        result.setName("服务器异常！");
        result.setDescr(e.getMessage());
        LOG.error(e);
        return result;
    }

    /**
     * 自定义示例运行时异常
     * @param e
     * @return
     */
    @ExceptionHandler(DemoRuntimeException.class)
    @ResponseBody
    public RestResponseDTO handleDemoRuntimeException(DemoRuntimeException e) {
        RestResponseDTO result = new RestResponseDTO();
        result.setStatus(false);
        result.setName("服务器异常！");
        result.setDescr(e.getMessage());
        LOG.error(e);
        return result;
    }
}
