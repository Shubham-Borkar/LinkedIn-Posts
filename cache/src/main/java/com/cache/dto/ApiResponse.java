package com.cache.dto;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ApiResponse {
private Object message;
private LocalDateTime timestamp;

public ApiResponse(Object message) {
	super();
	this.message = message;
	this.timestamp = LocalDateTime.now();
}
}
