package com.cache.config;

import java.util.Arrays;

import javax.cache.CacheManager;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

//@Configuration
//@EnableCaching
public class RedisConfig {

//
//	@Bean
//	public LettuceConnectionFactory connectionFactory() {
//		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
//		configuration.setHostName("localhost");
//		configuration.setPort(6379);
//		return new LettuceConnectionFactory(configuration);
//	}
//
//	@Bean
//	public RedisTemplate<Object, Object> redisTemplate() {
//		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
//		redisTemplate.setConnectionFactory(connectionFactory());
//		redisTemplate.setKeySerializer(new JdkSerializationRedisSerializer());
//		redisTemplate.setValueSerializer(new GenericToStringSerializer<>(Object.class));
//		return redisTemplate;
//	}
//
//	@Bean
//	@Primary
//	public RedisProperties properties() {
//		return new RedisProperties();
//
//	}
//	
//	@Bean
//	@Order(1)
//    public SimpleCacheManager cacheManager() {
//        SimpleCacheManager cacheManager = new SimpleCacheManager();
//        cacheManager.setCaches(Arrays.asList(
//            new ConcurrentMapCache("rproduct")
//        ));
//        return cacheManager;
//    }
}
