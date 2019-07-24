package com.ex.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;

@Configuration
public class LettuceConfig {
	
	@Value("${spring.redis.host}")
	private String redisHost;

	@Value("${spring.redis.password}")
	private String redisPassword;
	
	@Value("${spring.redis.port}")
	private int redisPort;
	
	@Bean(destroyMethod = "shutdown")
	ClientResources clientResources() {
		return DefaultClientResources.create();
	}

	@Bean(destroyMethod = "shutdown")
	RedisClient client(ClientResources clientResources) {
		RedisURI redisURI = RedisURI.create(redisHost, redisPort);
		redisURI.setPassword(redisPassword);
//		redisURI.setDatabase(9);
		return RedisClient.create(clientResources, redisURI);
	}

	@Bean(destroyMethod = "close")
	StatefulRedisConnection<String, String> connection(RedisClient client) {
		return client.connect();
	}
	
//	@Bean
//	public RedisConnectionFactory redisConnectionFactory() {
//		LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisHost, redisPort);
//		lettuceConnectionFactory.setPassword(redisPassword);
//		lettuceConnectionFactory.setDatabase(9);
//		System.out.println("REDIS : " + lettuceConnectionFactory + ", " + lettuceConnectionFactory);
//		return lettuceConnectionFactory;
//	}
//	
	@Bean(name="redisTemplate")
	public RedisTemplate<String, String> redisTemplate(LettuceConnectionFactory factory){
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(factory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		return redisTemplate;
	}

}
