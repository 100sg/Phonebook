package com.ex.demo.util;

public enum ErrorCode {
	
	DB_001("DB Connection Error"),
	DB_002("DB SQL Error"),
	DB_003("DB Select Error"),
	
	SERVER_001("Server Runtime Error"),
	SERVER_002("Server Response Error");
	
	private String message;
	ErrorCode(String msg){
		this.message = msg;
		getMessage();
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getResponseMessage(ErrorCode ec) {
		return "Error code : " + ec + "\nMessage : " + message;
	}

}
