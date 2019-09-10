package com.latico.archetype.springboot.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <PRE>
 * mybatis的配置
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-09-03 10:22
 * @Version: 1.0
 */
@Configuration
public class MybatisConfig {

    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public MybatisConfiguration mybatisConfiguration() {
        return new MybatisConfiguration();
    }
}
