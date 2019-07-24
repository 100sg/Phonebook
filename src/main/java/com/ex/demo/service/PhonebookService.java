package com.ex.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ex.demo.vo.Phonebook;

public interface PhonebookService {
	
	public List<Phonebook> findAll(); 
	public Phonebook findDetail(int id);
	public int update(Map<String, String> pb);
	public int insert(Map<String, String> pb) throws Exception;
	public int delete(int id);
}
