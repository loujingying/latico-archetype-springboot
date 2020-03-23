package com.latico.archetype.springboot.controller.aspect.pkg;

import com.latico.archetype.springboot.bean.bo.DemoTimeParam;
import com.latico.archetype.springboot.common.aspect.DemoPkgAspect;
import com.latico.archetype.springboot.service.DemoService;
import com.latico.commons.common.util.json.JacksonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <PRE>
 * 包目录方式的aspect
 * 访问该目录下所有方法都会被切面监听，因为在{@link DemoPkgAspect}中配置了扫描该目录
 * </PRE>
 * @author: latico
 * @date: 2019-06-07 01:33:49
 * @version: 1.0
 */
@RestController
@RequestMapping("aspect-pkg")
@Api("包目录方式的aspect演示API")
public class DemoPkgAspectController {

    @Autowired
    DemoService demoService;

    /**
     * @return 对象类型数据
     */
    @RequestMapping("serverTime")
    @ApiOperation("获取服务器时间参数API")
    public DemoTimeParam serverTime() {
        return demoService.serverTime();
    }

    /**
     * @return 字符串类型数据
     */
    @RequestMapping(value = "serverTimeStr")
    @ApiOperation("获取字符串类型服务器时间API")
    public String serverTimeStr() {
        //返回字符串，需要包一层JSON
        return JacksonUtils.objToJson("字符串类型:" + demoService.serverTime());
    }


}
