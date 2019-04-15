package com.my.myshop.config;

import com.my.myshop.redis.JedisClient;
import com.my.myshop.redis.JedisClientPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName JedisConfig
 * @Description TODO
 * @Auther QingBin Ding
 * @Date 2019/4/11 9:48
 * @Version 1.0
 **/
@Configuration
@Slf4j
public class JedisConfig{

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxActive;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWaitMillis;


    @Bean(name = "jedisPool")
    public JedisPool getJedisPool(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setMaxTotal(maxActive);
        config.setMinIdle(minIdle);
        JedisPool pool = new JedisPool(config,host,port,timeout,password);
        log.info("JedisPool注入成功！");
        log.info("redis地址：" + host + ":" + port);
        return pool;
    }

    @Bean
    @DependsOn("jedisPool")
    public JedisClient jedisClient() {
        JedisClient jedisClient = new JedisClientPool();
        return jedisClient;
    }


}
