package com.latico.archetype.springboot.client.type_common_feign;

import com.latico.archetype.springboot.bean.bo.DemoTimeParam;
import com.latico.archetype.springboot.bean.dto.RestRequestDTO;
import com.latico.commons.feign.FeignUtils;
import org.junit.Test;

import java.sql.Timestamp;

/**
 * <PRE>
 *  DemoControllerClient的测试用例
 *  
 * </PRE>
 * @Author: latico
 * @Date: 2019-06-27 10:54:20
 * @Version: 1.0
 */
public class DemoControllerClientTest {

    @Test
    public void serverTime() {

        DemoControllerClient serviceClient = FeignUtils.createProxyByJAXRSContractJson("http://127.0.0.1:8080/", DemoControllerClient.class);
        System.out.println(serviceClient.serverTime());
        System.out.println(serviceClient.serverTimeStr());

        serviceClient = FeignUtils.createProxyByJAXRSContract("http://127.0.0.1:8080/", DemoControllerClient.class);
        System.out.println(serviceClient.serverTimeStr());
    }
    @Test
    public void serverTimeBean(){
        DemoControllerClient serviceClient = FeignUtils.createProxyByJAXRSContractJson("http://127.0.0.1:8080/", DemoControllerClient.class);
        DemoTimeParam demoTimeParam = new DemoTimeParam();
        demoTimeParam.setTime(new Timestamp(System.currentTimeMillis()));
        System.out.println(demoTimeParam);
        RestRequestDTO<DemoTimeParam> restRequestDTO = new RestRequestDTO<>();
        restRequestDTO.setParam(demoTimeParam);
        restRequestDTO.setId("123");
        System.out.println(serviceClient.serverTimeBean(restRequestDTO));
    }
    @Test
    public void testRequestParam() {
        DemoControllerClient serviceClient = FeignUtils.createProxyByJAXRSContractJson("http://127.0.0.1:8080", DemoControllerClient.class);
        System.out.println(serviceClient.testRequestParam("abc"));
    }

    @Test
    public void testPathVariable() {
        DemoControllerClient serviceClient = FeignUtils.createProxyByJAXRSContractJson("http://127.0.0.1:8080", DemoControllerClient.class);
        System.out.println(serviceClient.testPathVariable("路径参数"));
    }

    @Test
    public void testMultiPathVariable() {
        DemoControllerClient serviceClient = FeignUtils.createProxyByJAXRSContractJson("http://127.0.0.1:8080", DemoControllerClient.class);
        System.out.println(serviceClient.testMultiPathVariable("路径参数key", "路径参数value"));
    }
}