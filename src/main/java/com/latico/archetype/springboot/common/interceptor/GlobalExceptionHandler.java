package com.latico.archetype.springboot.common.interceptor;

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
 * @Author: LanDingDong
 * @Date: 2019-04-18 09:06:05
 * @Version: 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RestResponseDTO handleException(Exception e) {
        RestResponseDTO result = new RestResponseDTO();
        result.setStatus(false);
        result.setName("服务器异常！");
        result.setDesc(e.getMessage());
        LOG.error(e);
        return result;
    }
}
