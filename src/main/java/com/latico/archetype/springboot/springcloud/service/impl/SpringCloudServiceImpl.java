package com.latico.archetype.springboot.springcloud.service.impl;

import com.latico.archetype.springboot.springcloud.bean.SpringCloudServiceInstance;
import com.latico.archetype.springboot.springcloud.bean.SpringCloudServiceStatus;
import com.latico.archetype.springboot.bean.dto.RestResponseDTO;
import com.latico.archetype.springboot.springcloud.service.SpringCloudService;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @author: latico
 * @date: 2020-04-13 9:28
 * @version: 1.0
 */
@Service
public class SpringCloudServiceImpl implements SpringCloudService {
    /** 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(SpringCloudServiceImpl.class);
    /**
     * 获取每一个服务下面实例
     * 测试时，可以使用多个端口启动服务实例
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    @GetMapping("service/instances")
    public RestResponseDTO<List<SpringCloudServiceStatus>> getAllServiceInstance() {
        RestResponseDTO<List<SpringCloudServiceStatus>> restResponseDTO = new RestResponseDTO<>();

        try {
            Map<String, List<SpringCloudServiceInstance>> msl = new HashMap<>();
            List<String> services = discoveryClient.getServices();
            for (String service : services) {
                List<ServiceInstance> sis = discoveryClient.getInstances(service);

                List<SpringCloudServiceInstance> list = new ArrayList<>();
                for (ServiceInstance si : sis) {
                    SpringCloudServiceInstance springCloudServiceInstance = new SpringCloudServiceInstance();
                    springCloudServiceInstance.setInstanceId(si.getInstanceId());
                    springCloudServiceInstance.setServiceId(si.getServiceId());
                    springCloudServiceInstance.setHost(si.getHost());
                    springCloudServiceInstance.setPort(si.getPort());
                    springCloudServiceInstance.setSecure(si.isSecure());
                    springCloudServiceInstance.setUri(si.getUri().toString());
                    springCloudServiceInstance.setScheme(si.getScheme());
                    springCloudServiceInstance.setMetadata(si.getMetadata());

                    list.add(springCloudServiceInstance);
                }
                msl.put(service, list);
            }

            List<SpringCloudServiceStatus> springCloudServiceInstances = new ArrayList<>();
            for (Map.Entry<String, List<SpringCloudServiceInstance>> entry : msl.entrySet()) {
                SpringCloudServiceStatus springCloudServiceInstance = new SpringCloudServiceStatus();
                springCloudServiceInstance.setServiceName(entry.getKey());
                springCloudServiceInstance.setServiceInstances(entry.getValue());

                springCloudServiceInstances.add(springCloudServiceInstance);
            }

            restResponseDTO.setDescr("成功");
            restResponseDTO.setStatus(true);
            restResponseDTO.setParam(springCloudServiceInstances);
        } catch (Exception e) {
            LOG.error("", e);
            restResponseDTO.setDescr("失败:" + e.getMessage());
            restResponseDTO.setStatus(false);
        }

        return restResponseDTO;
    }
}
