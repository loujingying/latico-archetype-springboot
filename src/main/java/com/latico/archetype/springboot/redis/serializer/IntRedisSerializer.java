package com.latico.archetype.springboot.redis.serializer;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * <PRE>
 * int的序列号
 * </PRE>
 *
 * @author: latico
 * @date: 2020-01-02 11:00
 * @version: 1.0
 */
public class IntRedisSerializer implements RedisSerializer<Integer> {

    @Override
    public byte[] serialize(Integer integer) throws SerializationException {
        if (integer != null) {
            return integer.toString().getBytes();
        }
        return new byte[0];
    }

    @Override
    public Integer deserialize(byte[] bytes) throws SerializationException {
        if (bytes != null && bytes.length >= 1) {
            return Integer.parseInt(new String(bytes));
        }
        return null;
    }
}
