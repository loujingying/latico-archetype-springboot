package com.latico.archetype.springboot.springcloud.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <PRE>
 * springcloud服务实例
 *
 {
 "serviceName": "latico-archetype-springboot",
 "serviceInstances": [
 {
 "instanceId": "localhost:latico-archetype-springboot:8080",
 "serviceId": "LATICO-ARCHETYPE-SPRINGBOOT",
 "host": "172.168.10.228",
 "port": 8080,
 "secure": false,
 "uri": "http://172.168.10.228:8080",
 "scheme": null,
 "metadata": {
 "management.port": "8080"
 }
 }
 ]
 }

 * </PRE>
 *
 * @author: latico
 * @date: 2020-04-13 9:29
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpringCloudServiceStatus {
    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 一个服务下的多个实例
     */
    private List<SpringCloudServiceInstance> serviceInstances;
}
