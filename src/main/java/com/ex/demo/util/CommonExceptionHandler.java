package com.ex.demo.util;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;


@ControllerAdvice
//@Order(Ordered.HIGHEST_PRECEDENCE) //주입순서를 결정할 수 있는 annotation
public class CommonExceptionHandler {
	
	Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);
	
	@ExceptionHandler(CommunicationsException.class)
	@ResponseBody
	public Map<String, Object> connectException(HttpServletRequest request, CommunicationsException ex){
		logger.info("Request : {}, raised : {}", request.getRequestURI(), ex.toString());
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("ErrorCode", "DB_001");
//		map.put("Message", ErrorCode.valueOf("DB_001"));
//		map.put("excpetion", ex.toString());
		return ResponseError.getResponse("DB_001", ErrorCode.DB_001, ex.toString());
	}
	
	@ExceptionHandler({SQLException.class, DataAccessException.class})
	@ResponseBody
	public Map<String, Object> databaseError(HttpServletRequest request, Exception ex){
		logger.info("Request : {}, raised : {}", request.getRequestURI(), ex.toString());
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("ErrorCode", "DB_002");
//		map.put("Message", ErrorCode.valueOf("DB_002"));
//		map.put("excpetion", ex.toString());
		return ResponseError.getResponse("DB_002", ErrorCode.DB_002, ex.toString());
	}
	
	@ExceptionHandler({RuntimeException.class})
	@ResponseBody
	public Map<String, Object> runtimeError(HttpServletRequest request, RuntimeException ex){
		logger.info("Request : {}, raised : {}", request.getRequestURI(), ex.toString());
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("ErrorCode", "SERVER_001");
//		map.put("Message", ErrorCode.SERVER_001);
//		map.put("excpetion", ex.toString());
		return ResponseError.getResponse("SERVER_001", ErrorCode.SERVER_001, ex.toString());
		
	}
	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<?> common(Exception ex){
//		logger.info(ex.toString());
//		return new ResponseEntity<>(ErrorCode.valueOf("SERVER_001").getResponseMessage(ErrorCode.SERVER_001), HttpStatus.FORBIDDEN);
//	}
	
}
