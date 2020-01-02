package com.latico.archetype.springboot.config.redis;


import com.latico.archetype.springboot.config.redis.serializer.IntRedisSerializer;
import com.latico.archetype.springboot.config.redis.serializer.LongRedisSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * <PRE>
 * redis的配置类
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-12-31 16:29
 * @Version: 1.0
 */
@Configuration
public class RedisConfig {
    /**
     * 连接工厂
     */
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * int值类型的redis操作模板
     * @return
     */
    @Bean
    public RedisTemplate<String, Integer> intRedisTemplate() {
        RedisTemplate<String, Integer> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new IntRedisSerializer());
        return redisTemplate;
    }

    /**
     * long值类型的redis操作模板
     * @return
     */
    @Bean
    public RedisTemplate<String, Long> longRedisTemplate() {
        RedisTemplate<String, Long> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new LongRedisSerializer());
        return redisTemplate;
    }

}
