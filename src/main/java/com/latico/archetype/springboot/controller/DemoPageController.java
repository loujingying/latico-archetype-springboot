package com.latico.archetype.springboot.controller;

import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
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
 * @Author: latico
 * @Date: 2019-02-27 22:10
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = "demo")
@Api(description = "演示返回网页API")
public class DemoPageController {
    private static final Logger LOG = LoggerFactory.getLogger(DemoPageController.class);

    @RequestMapping("hello")
    @ApiOperation("返回动态类型的hello.html网页API")
    public ModelAndView hello() {
        LOG.info("调用了页面接口");
        ModelAndView modelAndView = new ModelAndView();
        //templates目录下的
        modelAndView.setViewName("page");
        modelAndView.addObject("city", "北京");
        modelAndView.addObject("name", "长城");
        return modelAndView;
    }
}
