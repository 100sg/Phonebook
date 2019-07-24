package com.ex.demo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ex.demo.vo.Phonebook;

public interface PhonebookDao {
	public List<Phonebook> selectList(); 
	public Phonebook selectDetail(int num);
	public int insertData(Map<String, String> pb);
	public int updateData(Map<String, String> pb);
	public int delData(int num);
}
