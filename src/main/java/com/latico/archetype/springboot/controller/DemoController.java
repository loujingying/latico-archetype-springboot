package com.latico.archetype.springboot.controller;

import com.latico.archetype.springboot.bean.bo.DemoByPageResult;
import com.latico.archetype.springboot.bean.bo.DemoPageRequestParam;
import com.latico.archetype.springboot.bean.bo.DemoTimeParam;
import com.latico.archetype.springboot.bean.dto.RestRequestDTO;
import com.latico.archetype.springboot.bean.dto.RestResponseDTO;
import com.latico.archetype.springboot.common.anno.TimeStatisAspectAnnotation;
import com.latico.archetype.springboot.common.util.HttpUtils;
import com.latico.archetype.springboot.config.xml.XmlBizConfig;
import com.latico.archetype.springboot.config.yaml.YamlBizConfig;
import com.latico.archetype.springboot.dao.primary.entity.Demo;
import com.latico.archetype.springboot.dao.secondary.entity.Demo2;
import com.latico.archetype.springboot.service.DemoService;
import com.latico.commons.common.util.io.FileUtils;
import com.latico.commons.common.util.io.IOUtils;
import com.latico.commons.common.util.json.JacksonUtils;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <PRE>
 *  演示
 *  启动后的访问方式有：
 *  1、浏览器；
 *  2、客户端API；
 *  3、Postman；
 *  4、SoapUI
 *
 * </PRE>
 * @author: latico
 * @date: 2019-06-07 01:33:49
 * @version: 1.0
 */
@RestController
@RequestMapping("demo")
@Api("演示API")
public class DemoController {

    private static final Logger LOG = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    DemoService demoService;

    /**
     * @return 查询数据库所有Demo数据
     */
    @RequestMapping(value = "insertAndSelectDemo")
    @ApiOperation("测试第一个数据源查询Demo表API")
    public List<Demo> insertAndSelectDemo(@Context HttpServletRequest httpServletRequest, @Context HttpServletResponse httpServletResponse) {
        return demoService.insertAndSelectDemo(httpServletRequest, httpServletResponse);
    }

    @RequestMapping(value = "selectDemo2")
    @ApiOperation("测试第二个数据源查询Demo表API")
    public List<Demo2> selectDemo2(@Context HttpServletRequest httpServletRequest, @Context HttpServletResponse httpServletResponse) {
        return demoService.selectDemo2(httpServletRequest, httpServletResponse);
    }

    @RequestMapping(value = "queryDemoByPage")
    @ApiOperation("测试分页查询Demo表API")
    public DemoByPageResult queryDemoByPage(@RequestBody DemoPageRequestParam demoPageRequestParam, @Context HttpServletRequest httpServletRequest, @Context HttpServletResponse httpServletResponse) {
        return demoService.queryDemoByPage(demoPageRequestParam.getPageNum(), demoPageRequestParam.getPageSize(), httpServletRequest, httpServletResponse);
    }

    @RequestMapping(value = "queryAllDemo")
    @ApiOperation("测试所有数据源查询demo表API")
    public String queryAllDemo() {
        return demoService.queryAllDemo();
    }

    /**
     * 测试读取yaml业务配置文件的方式2，单例读取配置方式
     * @return
     */
    @GetMapping("testYamlConfigType2")
    @ApiOperation("测试读取yaml业务配置文件的方式2，单例读取配置方式")
    public String testYamlConfigType2() {
        return YamlBizConfig.getInstance().toString();
    }

    /**
     * 测试读取xml业务配置文件的方式，单例读取配置方式
     * @return
     */
    @GetMapping("testXmlConfigType")
    @ApiOperation("测试读取xml业务配置文件的方式，单例读取配置方式")
    public String testXmlConfigType() {
        return XmlBizConfig.getInstance().getId() + "";
    }

    /**
     * @return 对象类型数据
     */
    @TimeStatisAspectAnnotation
    @RequestMapping("serverTime")
    @ApiOperation("获取服务器时间参数API")
    public DemoTimeParam serverTime(@Context HttpServletRequest httpServletRequest, @Context HttpServletResponse httpServletResponse) {
        String requestUri = httpServletRequest.getRequestURI();
        String remoteHost = httpServletRequest.getRemoteHost();
        LOG.info("调用的客户端信息,地址:{} 请求路径:{}", remoteHost, requestUri);

        return demoService.serverTime();
    }

    /**
     * @return 字符串类型数据
     */
    @TimeStatisAspectAnnotation
    @RequestMapping(value = "serverTimeStr")
    @ApiOperation("获取字符串类型服务器时间API")
    public String serverTimeStr() {
        //返回字符串，需要包一层JSON
        return JacksonUtils.objToJson("字符串类型:" + demoService.serverTime());
    }

    /**
     * @return 对象类型数据
     */
    @TimeStatisAspectAnnotation
    @RequestMapping(value = "serverTimeBean", method = {RequestMethod.POST})
    @ApiOperation("获取服务器时间API")
    public RestResponseDTO<DemoTimeParam> serverTimeBean(@RequestBody RestRequestDTO<DemoTimeParam> restRequestDTO) {
        RestResponseDTO<DemoTimeParam> restResponseDTO = new RestResponseDTO<>();
        System.out.println("接收到的时间:" + restRequestDTO);
        DemoTimeParam demoTimeParam = demoService.serverTime();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        demoTimeParam.setTime(new Date(System.currentTimeMillis()));
        System.out.println("响应的时间:" + demoTimeParam);
        restResponseDTO.setParam(demoTimeParam);
        restResponseDTO.setId(restRequestDTO.getId());
        return restResponseDTO;
    }

    /**
     * 测试URL中的键值对参数，可以多个 RequestParam
     * @return 字符串类型数据
     */
    @TimeStatisAspectAnnotation
    @RequestMapping(value = "testRequestParam")
    @ApiOperation("测试请求参数API")
    public String testRequestParam(@RequestParam("name") String name) {
        //返回字符串，需要包一层JSON
        return JacksonUtils.objToJson("测试RequestParam:" + name);
    }

    /**
     * 测试路径中的参数 PathVariable
     * @return 字符串类型数据
     */
    @TimeStatisAspectAnnotation
    @RequestMapping(value = "testPathVariable/{name}")
    @ApiOperation("获测试路径变量API")
    public String testPathVariable(@PathVariable String name) {
        //返回字符串，需要包一层JSON
        return JacksonUtils.objToJson("测试PathVariable:" + name);
    }

    /**
     * 测试路径中的多参数 多路径参数，PathVariable
     * @return 字符串类型数据
     */
    @TimeStatisAspectAnnotation
    @RequestMapping(value = "testMultiPathVariable/{name}/{value}")
    @ApiOperation("测试路径多变量API")
    public String testMultiPathVariable(@PathVariable Map<String, String> map) {
        //返回字符串，需要包一层JSON
        return JacksonUtils.objToJson("测试testMultiPathVariable:" + map.get("name") + "/" + map.get("value"));
    }

    /**
     * <PRE>
     *  下载文件示例
     * </PRE>
     * @author: latico
     * @date: 2020-03-27 23:45:11
     * @version: 1.0
     */
    @GetMapping("downloadFile")
    @ApiOperation("下载文件示例")
    public String downloadFile(@Context HttpServletRequest httpServletRequest, @Context HttpServletResponse httpServletResponse) {
        HttpUtils.downloadFile(httpServletResponse, "./config/README.md");
        return "成功";
    }

    /**
     * 处理文件上传，客户端可以通过postman或者使用文件上传表单进行上传，上传后，可以存储到本地文件，或者大数据系统中
     *  <form action="demo/uploadFile" method="post" enctype="multipart/form-data">
     *         <p><input type="file" name="fileName"/></p>
     *         <p><input type="submit" value="上传文件"/></p>
     *     </form>
     * @param multipartFile
     * @return
     */
    @RequestMapping(value="uploadFile", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("fileName") MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        //设置文件上传路径
        String fileDir = "upload";
        try {
            FileUtils.writeByteArrayToFile(new File(fileDir + "/" + fileName), multipartFile.getBytes());
            return "上传成功";
        } catch (Exception e) {
            LOG.error("", e);
            return "上传失败";
        }
    }

}
