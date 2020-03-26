package com.latico.archetype.springboot.hadoop.mapreduce;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * MapReduce处理控制层
 * @author linhaiy
 * @date 2019.05.18
 */
@RestController
@RequestMapping("/hadoop/reduce")
public class MapReduceAction {
 
	@Autowired
	MapReduceService mapReduceService;
 
	/**
	 * 一年最高气温统计
	 * @param jobName
	 * @param inputPath
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "MaxWeather", method = RequestMethod.POST)
	@ResponseBody
	public String weather(@RequestParam("jobName") String jobName, @RequestParam("inputPath") String inputPath)
			throws Exception {
		if (StringUtils.isEmpty(jobName) || StringUtils.isEmpty(inputPath)) {
			return "请求参数为空";
		}
		mapReduceService.weather(jobName, inputPath);
		return "温度统计成功";
	}
}
 