package com.latico.archetype.springboot.controller;

import com.latico.archetype.springboot.springcloud.bean.SpringCloudServiceStatus;
import com.latico.archetype.springboot.bean.dto.RestResponseDTO;
import com.latico.archetype.springboot.springcloud.service.SpringCloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <PRE>
 * springcloud的相关的
 * </PRE>
 * @author: latico
 * @date: 2020-03-27 10:27:40
 * @version: 1.0
 */
@RestController
@RequestMapping("springcloud")
public class SpringCloudController {

    /**
     * 获取每一个服务下面实例
     * 测试时，可以使用多个端口启动服务实例
     */
    @Autowired
    private SpringCloudService springCloudService;

    @GetMapping("service/instances")
    public RestResponseDTO<List<SpringCloudServiceStatus>> getAllServiceInstance() {
        return springCloudService.getAllServiceInstance();
    }
    
}