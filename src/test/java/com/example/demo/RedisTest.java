package com.example.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

	@Autowired
	private RedisTemplate redisTemplate; 
	
	@Test
	public void testDataHandling() throws Exception {
		String key = "skbaek";
		redisTemplate.opsForValue().set(key, "value test");
		String value = (String) redisTemplate.opsForValue().get(key);
		
		Assert.assertEquals("value test", value);
				
	}
	
}
