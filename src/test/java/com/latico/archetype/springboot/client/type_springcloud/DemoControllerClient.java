package com.latico.archetype.springboot.client.type_springcloud;

import com.latico.archetype.springboot.bean.bo.DemoTimeParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-03-05 16:13
 * @Version: 1.0
 */
@FeignClient("latico-archetype-springboot") //声明调用的服务名称，自带负载均衡
@RequestMapping(value = "demo")
public interface DemoControllerClient {

    /**
     * @return 对象类型数据
     */
    @RequestMapping("serverTime")
    public DemoTimeParam serverTime();


    /**
     * 入参和出参都是对象
     * @param timeBean
     * @return
     */
    @PostMapping(value = "serverTimeBean")
    public DemoTimeParam serverTimeBean(@RequestBody DemoTimeParam timeBean);


    /**
     * @return 字符串类型数据
     */
    @RequestMapping(value = "serverTimeStr")
    public String serverTimeStr();

    /**
     * 测试URL中的键值对参数，可以多个 RestRequestDTO
     *
     * @return 字符串类型数据
     */
    @RequestMapping(value = "testRequestParam")
    public String testRequestParam(@RequestParam("name") String name);

    /**
     * 测试路径中的参数 PathVariable
     *
     * @return 字符串类型数据
     */
    @RequestMapping(value = "testPathVariable/{name}")
    public String testPathVariable(@PathVariable(value = "name") String name);

    /**
     * 测试路径中的多参数 多路径参数，PathVariable
     *
     * @return 字符串类型数据
     */
    @RequestMapping(value = "testMultiPathVariable/{name}/{value}")
    public String testMultiPathVariable(@PathVariable(value = "name") String name, @PathVariable(value = "value") String value);

}
