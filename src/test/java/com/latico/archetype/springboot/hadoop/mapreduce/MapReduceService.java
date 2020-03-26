package com.latico.archetype.springboot.hadoop.mapreduce;

import com.latico.archetype.springboot.hadoop.service.HdfsService;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
/**
 * 单词统计
 * @author linhaiy
 * @date 2019.05.18
 */
@Service
public class MapReduceService {
 
	// 默认reduce输出目录
	private static final String OUTPUT_PATH = "/output";

	@Autowired
	HdfsService hdfsService;
	/**
	 * 一年最高气温统计
	 * @param jobName
	 * @param inputPath
	 * @throws Exception
	 */
	public void weather(String jobName, String inputPath) throws Exception {
		if (StringUtils.isEmpty(jobName) || StringUtils.isEmpty(inputPath)) {
			return;
		}
		// 输出目录 = output/当前Job
		String outputPath = OUTPUT_PATH + "/" + jobName;
		if (hdfsService.existFile(outputPath)) {
			hdfsService.deleteFile(outputPath);
		}
		JobConf jobConf = ReduceJobsUtils.getWeatherJobsConf(jobName);
		FileInputFormat.setInputPaths(jobConf, new Path(inputPath));
		FileOutputFormat.setOutputPath(jobConf, new Path(outputPath));
		JobClient.runJob(jobConf);
	}
}