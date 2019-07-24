package com.ex.demo.util;

import java.util.HashMap;
import java.util.Map;

public class ResponseError {
	
	String code;
	String message;
	String detail;
	
	ResponseError(){
	}
	
	public static Map<String, Object> getResponse(String code, ErrorCode msg, String ex) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("ErrorCode", code);
		map.put("Message", msg);
		map.put("excpetion", ex);
		
		return map;
		
	}

}
