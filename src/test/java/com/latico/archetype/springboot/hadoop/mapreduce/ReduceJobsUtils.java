package com.latico.archetype.springboot.hadoop.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
 

/**
 * Map/Reduce工具类
 * 
 * @author linhaiy
 * @date 2019.05.18
 */
@Component
public class ReduceJobsUtils {
 
	@Value("${hdfs.path}")
	private String path;
 
	private static String hdfsPath;
 
	/**
	 * 获取HDFS配置信息
	 * @return
	 */
	public static Configuration getConfiguration() {
		Configuration configuration = new Configuration();
		configuration.set("fs.defaultFS", hdfsPath);
		configuration.set("mapred.job.tracker", hdfsPath);
		// 运行在yarn的集群模式
		// configuration.set("mapreduce.framework.name", "yarn");
		// 这个配置是让main方法寻找该机器的mr环境
		// configuration.set("yarn.resourcemanmager.hostname", "node1");
		return configuration;
	}
 
	/**
	 * 获取单词一年最高气温计算配置
	 * @param jobName
	 * @return
	 */
	public static JobConf getWeatherJobsConf(String jobName) {
		JobConf jobConf = new JobConf(getConfiguration());
		jobConf.setJobName(jobName);
		jobConf.setOutputKeyClass(Text.class);
		jobConf.setOutputValueClass(LongWritable.class);
		jobConf.setMapperClass(WeatherMap.class);
		jobConf.setReducerClass(WeatherReduce.class);

		//
		jobConf.setInputFormat(TextInputFormat.class);
		jobConf.setOutputFormat(TextOutputFormat.class);

		return jobConf;
	}
 
	@PostConstruct
	public void getPath() {
		hdfsPath = this.path;
	}
 
	public static String getHdfsPath() {
		return hdfsPath;
	}
}