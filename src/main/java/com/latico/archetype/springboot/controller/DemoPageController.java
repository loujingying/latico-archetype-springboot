package com.latico.archetype.springboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @Author: LanDingDong
 * @Date: 2019-02-27 22:10
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = "demo")
@Api(description = "演示返回网页API")
public class DemoPageController {

    @RequestMapping("hello")
    @ApiOperation("返回动态类型的hello.html网页API")
    public ModelAndView hello() {
        ModelAndView modelAndView = new ModelAndView();
        //templates目录下的
        modelAndView.setViewName("hello.html");
        modelAndView.addObject("city", "北京");
        modelAndView.addObject("name", "长城");
        return modelAndView;
    }
}
