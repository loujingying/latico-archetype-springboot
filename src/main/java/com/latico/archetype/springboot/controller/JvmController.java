package com.latico.archetype.springboot.controller;

import com.latico.archetype.springboot.bean.dto.RestResponseDTO;
import com.latico.archetype.springboot.jvm.bean.JvmPerfInfo;
import com.latico.archetype.springboot.jvm.service.JvmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <PRE>
 * JVM相关
 * </PRE>
 * @author: latico
 * @date: 2020-03-27 10:27:40
 * @version: 1.0
 */
@RestController
@RequestMapping("jvm")
public class JvmController {

    @Autowired
    private JvmService jvmService;

    @GetMapping("queryCurrentPerf")
    public RestResponseDTO<JvmPerfInfo> queryCurrentPerf() {
        return jvmService.queryCurrentPerf();
    }
    
}