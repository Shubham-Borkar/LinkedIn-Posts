package com.app.globalexceptionhandler;





import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( code = HttpStatus.BAD_REQUEST,reason = "Error using Annotation")
public class StatusCodeException extends Exception {
	private static final long serialVersionUID = 1L;

	public StatusCodeException(String message) {
		super(message);
	}

}
