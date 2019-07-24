package com.ex.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ex.demo.dao.PhonebookDao;
import com.ex.demo.handler.RedisHandler;
import com.ex.demo.service.PhonebookService;
import com.ex.demo.vo.Phonebook;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.jdbc.Field;

import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

@Service
public class PhonebookServiceImpl implements PhonebookService {
	Logger logger = LoggerFactory.getLogger(PhonebookServiceImpl.class);

	@Autowired
	PhonebookDao dao;
	
	@Autowired
	StatefulRedisConnection<String, String> connection;
	
	@Autowired
	RedisTemplate redisTemplate;
	
	@Autowired
	RedisHandler redisHandler;
	
	public Phonebook setPhonebook(Map<String, String> pb) {
		Phonebook data = new Phonebook();
		data.setTbAccountUser(pb.get("tbAccountUser"));
		data.setName(pb.get("name"));
		data.setPhoneNumber(pb.get("phoneNumber"));
		data.setEmail(pb.get("email"));
		data.setBirthday(pb.get("birthday"));
		data.setMemo(pb.get("memo"));
		data.setAddress(pb.get("address"));
		return data;
	}
	
	@Override
	public List<Phonebook> findAll() {
		
		Map<String, List<String>> taskMap = new HashMap<>();
		ObjectMapper mapper = null;
		RedisCommands<String, String> sysCommands = connection.sync();
		List<Phonebook> list = new ArrayList<>();
		try {
			logger.info("DBSize : {}", sysCommands.dbsize() );
			if(sysCommands.dbsize() == 0) {
				for(Phonebook listPh : dao.selectList()) {
					redisTemplate.opsForValue().set(listPh.getName(), listPh);
//					logger.info("key : {}, field : {}, value : {}", listPh.getTbAccountUser(), listPh.getName(), listPh);
//					logger.info("getData : {}", redisTemplate.opsForValue().get(listPh.getName()) );
				}
			} 
			
			List<String> key = sysCommands.keys("*");
			logger.info("key list size : {}", key.size() );
			for(int i=0; i<key.size(); i++) {
				list.add((Phonebook)redisTemplate.opsForValue().get(key.get(i)));
			}
			
			logger.info("List data : {}", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public Phonebook findDetail(int num) {
		return dao.selectDetail(num);
	}

	@Transactional
	@Override
	public int update(Map<String, String> pb) {
		Phonebook data = setPhonebook(pb);
		
		redisTemplate.opsForValue().set(data.getName(), data);

		return dao.updateData(pb);
	}

	@Transactional
	@Override
	public int insert(Map<String, String> pb) throws Exception {
		Phonebook data = setPhonebook(pb);
		logger.info("redis data : {}, map : {}" , data, pb);
		
		try {
			redisTemplate.opsForValue().set(data.getName(), data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dao.insertData(pb);
	}

	@Transactional
	@Override
	public int delete(int id) {
		
		Phonebook data = findDetail(id);
		redisTemplate.delete(data.getName()); 
		
		
		return dao.delData(id);
	}

}
