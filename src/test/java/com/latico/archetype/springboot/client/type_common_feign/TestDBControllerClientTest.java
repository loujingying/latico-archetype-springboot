package com.latico.archetype.springboot.client.type_common_feign;

import com.latico.commons.feign.FeignUtils;
import org.junit.Test;

/**
 * <PRE>
 * TestDBControllerClient的测试用例
 *
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-06-27 10:45:56
 * @Version: 1.0
 */
public class TestDBControllerClientTest {

    @Test
    public void test() {
        TestDBControllerClient client = FeignUtils.createProxyByJAXRSContractJson("http://127.0.0.1:8080/", TestDBControllerClient.class);
        System.out.println(client.selectDemo());
        System.out.println(client.selectDemo2());

    }

    @Test
    public void selectDemo() {
        TestDBControllerClient client = FeignUtils.createProxyByJAXRSContractJson("http://127.0.0.1:8080/", TestDBControllerClient.class);
        System.out.println(client.selectDemo());

    }

    @Test
    public void selectDemo2() {
        TestDBControllerClient client = FeignUtils.createProxyByJAXRSContractJson("http://127.0.0.1:8080/", TestDBControllerClient.class);
        System.out.println(client.selectDemo2());

    }
}