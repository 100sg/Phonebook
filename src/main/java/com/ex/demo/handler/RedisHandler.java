package com.ex.demo.handler;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ex.demo.vo.Phonebook;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;


@Service
public class RedisHandler{
	
	@Autowired
	StatefulRedisConnection<String, String> connection;
	
	@Autowired
	RedisTemplate redisTemplate;
	
	public RedisCommands<String, String> setResourceSync(){
		RedisCommands<String, String> sysCommands = connection.sync();
		return sysCommands;
	}
	
	public List<?> setList(){
		RedisCommands<String, String> commands = setResourceSync(); 
		List<String> key = commands.keys("*");
		List<Object> list = new ArrayList<>();

		for(int i=0; i<key.size(); i++) {
			list.add(redisTemplate.opsForValue().get(key.get(i)));
		}
		return list;
	}
	
}
