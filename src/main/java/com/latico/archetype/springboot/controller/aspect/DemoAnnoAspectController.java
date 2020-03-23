package com.latico.archetype.springboot.controller.aspect;

import com.latico.archetype.springboot.bean.bo.DemoTimeParam;
import com.latico.archetype.springboot.common.anno.DemoAspectAnnotation;
import com.latico.archetype.springboot.service.DemoService;
import com.latico.commons.common.util.json.JacksonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <PRE>
 * 注解方式的aspect监听，增加了{@link DemoAspectAnnotation}注解的方法会被监听
 * </PRE>
 * @author: latico
 * @date: 2019-06-07 01:33:49
 * @version: 1.0
 */
@RestController
@RequestMapping("aspect-anno")
@Api("注解方式的aspect演示API")
public class DemoAnnoAspectController {

    @Autowired
    DemoService demoService;

    /**
     * 该方法添加了LogAnnotation注解，会被aspect监听
     * @return 对象类型数据
     */
    @DemoAspectAnnotation
    @RequestMapping("serverTime")
    @ApiOperation("获取服务器时间参数API")
    public DemoTimeParam serverTime() {
        return demoService.serverTime();
    }

    /**
     * 该方法不加，所以不会被监听
     * @return 字符串类型数据
     */
    @RequestMapping(value = "serverTimeStr")
    @ApiOperation("获取字符串类型服务器时间API")
    public String serverTimeStr() {
        //返回字符串，需要包一层JSON
        return JacksonUtils.objToJson("字符串类型:" + demoService.serverTime());
    }


}
