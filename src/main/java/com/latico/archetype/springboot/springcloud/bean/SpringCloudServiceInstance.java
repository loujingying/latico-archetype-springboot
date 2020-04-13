package com.latico.archetype.springboot.springcloud.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @author: latico
 * @date: 2020-04-13 9:45
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpringCloudServiceInstance {

    private String instanceId;

    private String serviceId;

    private String host;

    private int port;

    private boolean secure;

    private String uri;

    private String scheme;

    private Map<String, String> metadata;

}
