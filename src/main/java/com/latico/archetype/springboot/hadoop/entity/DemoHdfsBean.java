package com.latico.archetype.springboot.hadoop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * <PRE>
 *  用户实体类
 * </PRE>
 * @author: latico
 * @date: 2020-03-23 16:55:49
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoHdfsBean implements Writable {

	private String username;
	private Integer age;
	private String address;

	@Override
	public void write(DataOutput output) throws IOException {
		// 把对象序列化
		output.writeChars(username);
		output.writeInt(age);
		output.writeChars(address);
	}

	@Override
	public void readFields(DataInput input) throws IOException {
		// 把序列化的对象读取到内存中
		username = input.readUTF();
		age = input.readInt();
		address = input.readUTF();
	}

}