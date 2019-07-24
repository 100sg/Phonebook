package com.ex.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ex.demo.service.PhonebookService;
import com.ex.demo.vo.Phonebook;


@RestController
@RequestMapping("/api")
public class TestController {
	Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	PhonebookService mService;
	
	@GetMapping("/")
	public ResponseEntity<String> test(){
		logger.info("test");
		
		return new ResponseEntity<>("hello", HttpStatus.OK);
	}
	
	@GetMapping("/list")
	public List<Phonebook> list() {
		List<Phonebook> list = null;
		list = mService.findAll();
		logger.info("All List");

		return list; 
	}
	
	@PostMapping("/detail")
	public Phonebook detail(@RequestBody Map<String, String> param) {
		logger.info("detail");

		Phonebook ph = mService.findDetail( Integer.parseInt(param.get("id")) );
		return ph;
	}
	
	@PostMapping("/insert")
	public void insert(@RequestBody Map<String, String> param) throws Exception{
		logger.info("insert");
		
		mService.insert(param);
	}
	
	@PostMapping("/edit")
	public void edit(@RequestBody Map<String, String> param) throws Exception{
		logger.info("update");
	
		mService.update(param);
	}
	
	@PostMapping("/del")
	public void delete(@RequestBody Map<String, String> param) throws Exception {
		logger.info("delete");

		mService.delete( Integer.parseInt(param.get("id")) );
	}
	
}

