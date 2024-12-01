package com.app.globalexceptionhandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.dto.ApiResponse;


@ControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		List<FieldError> errList=ex.getFieldErrors();
		Map<String, String> errMap=new HashMap<>();
		for(FieldError err : errList)
			errMap.put(err.getField(), err.getDefaultMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(errMap));
	}
	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<?> handlerForAllException(MethodArgumentNotValidException ex) {
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("In Global Exception Handler - Server Error"));
//	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> handlerForHttpMessageNotReadableException(MethodArgumentNotValidException ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("In Global Exception Handler - HttpMessageNotReadableException JSON parse Error "));
	}
	
	
	

}
