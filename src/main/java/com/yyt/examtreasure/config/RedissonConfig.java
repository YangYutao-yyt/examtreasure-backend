package com.yyt.examtreasure.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.redisson.api.RedissonClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Redisson 配置
 *
 */
@Configuration
//读取yml的redis配置
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedissonConfig {
    //redis配置传递
    //地址
    private String host;
    //端口号
    private Integer port;
    //库号
    private Integer database;
    //密码
    private String password;

    //初始化客户端
    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        //使用单个redis
        config.useSingleServer()
                .setAddress("redis://" + host + ":" + port)
                .setDatabase(database)
                .setPassword(password);
        return Redisson.create(config);

    }
}
