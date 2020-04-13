package com.latico.archetype.springboot.springcloud.service;

import com.latico.archetype.springboot.springcloud.bean.SpringCloudServiceStatus;
import com.latico.archetype.springboot.bean.dto.RestResponseDTO;

import java.util.List;

/**
 * <PRE>
 * springcloud服务操作
 * </PRE>
 *
 * @author: latico
 * @date: 2020-04-13 9:27
 * @version: 1.0
 */
public interface SpringCloudService {

    /**
     * 查询所有服务列表
     * @return
     */
    RestResponseDTO<List<SpringCloudServiceStatus>> getAllServiceInstance();
}
