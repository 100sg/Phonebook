package com.ex.demo.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Phonebook implements Serializable{

	private String tbAccountUser;
	private int no;
	private String name; 
	private String phoneNumber; 
	private String email; 
	private String address; 
	private String birthday; 
	private String memo;
	
	public String getTbAccountUser() {
		return tbAccountUser;
	}
	public void setTbAccountUser(String tbAccountUser) {
		this.tbAccountUser = tbAccountUser;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Override
	public String toString() {
		return "Phonebook [tbAccountUser=" + tbAccountUser + ", no=" + no + ", name=" + name + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", address=" + address + ", birthday=" + birthday + ", memo="
				+ memo + "]";
	} 
	
}
