package com.latico.archetype.springboot.client.type_springcloud;


import com.latico.archetype.springboot.dao.primary.entity.Demo;
import com.latico.archetype.springboot.dao.secondary.entity.Demo2;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <PRE>
 @RequestMapping(value = "selectDemo")
 public List<Demo> selectDemo() {
 return demoRepository.findAll();
 }

 @RequestMapping(value = "selectDemo2")
 public List<Demo2> selectDemo2() {
 return demo2Mapper.findAll();
 }
 * </PRE>
 *
 * @Author: LanDingDong
 * @Date: 2019-03-05 16:13
 * @Version: 1.0
 */
@FeignClient("demo") //声明调用的服务名称，自带负载均衡
@RequestMapping(value = "test")
public interface TestControllerClient {

    @RequestMapping("selectDemo")
    public List<Demo> selectDemo();

    @RequestMapping("selectDemo2")
    public List<Demo2> selectDemo2();

}
