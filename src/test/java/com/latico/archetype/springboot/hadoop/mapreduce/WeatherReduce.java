package com.latico.archetype.springboot.hadoop.mapreduce;
 
import java.io.IOException;
import java.util.Iterator;
 
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
 
/**
 * 统计一年天气最高温
 * @author linhaiy
 * @date 2019.05.18
 */
public class WeatherReduce extends MapReduceBase implements Reducer<Text, LongWritable, Text, LongWritable> {
 
	@Override
	public void reduce(Text key, Iterator<LongWritable> values, OutputCollector<Text, LongWritable> output,
			Reporter reporter) throws IOException {
		long maxValue = Integer.MIN_VALUE;
		StringBuffer sb = new StringBuffer();
 
		// 取values温度的最大值
		while (values.hasNext()) {
			long tmp = values.next().get();
			maxValue = Math.max(maxValue, tmp);
			sb.append(tmp).append(", ");
 
			output.collect(key, new LongWritable(maxValue));
		}
 
		// 打印输入样本，如 2000， 15 ，99， 12
		System.out.println("==== Before Reduce ==== " + key + ", " + sb.toString());
		// 打印输出样本
		System.out.println("==== After Reduce ==== " + key + ", " + sb.toString());
	}
 
}