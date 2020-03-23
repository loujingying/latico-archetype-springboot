package com.latico.archetype.springboot.redis.serializer;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * <PRE>
 * long的序列号
 * </PRE>
 *
 * @author: latico
 * @date: 2020-01-02 11:00
 * @version: 1.0
 */
public class LongRedisSerializer implements RedisSerializer<Long> {
    @Override
    public byte[] serialize(Long aLong) throws SerializationException {
        if (aLong != null) {
            return aLong.toString().getBytes();
        }
        return new byte[0];
    }

    @Override
    public Long deserialize(byte[] bytes) throws SerializationException {
        if (bytes != null && bytes.length >= 1) {
            return Long.parseLong(new String(bytes));
        }
        return null;
    }

}
