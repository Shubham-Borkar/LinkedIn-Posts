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
import org.springframework.web.context.request.WebRequest;

import com.app.dto.ApiResponse;


@ControllerAdvice
public class CustomExceptionHandler {
	
	
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        String apiEndpoint = request.getDescription(false);
        String apiDescription = request.getDescription(true);
        
        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // String username = authentication != null ? authentication.getName() : "anonymous";
        
        String errorMessage = ex.getMessage();
        
        System.err.println("\n____________  Retreiving Error info in Exception Handler ____________\n"+
        				   "Exception in API Endpoint  -> "+apiEndpoint);
        System.err.println("Exception message  -> "+errorMessage);
        System.err.println("Exception description (endpoint + ip address) -> "+apiDescription +"\n");
     
        
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
    
    
    
    
    
    
    
    
    
    
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		List<FieldError> errList=ex.getFieldErrors();
		Map<String, String> errMap=new HashMap<>();
		for(FieldError err : errList)
			errMap.put(err.getField(), err.getDefaultMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(errMap));
	}
	

	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> handlerForHttpMessageNotReadableException(MethodArgumentNotValidException ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("In Global Exception Handler - HttpMessageNotReadableException JSON parse Error "));
	}

	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<?> handlerForAllException(MethodArgumentNotValidException ex) {
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("In Global Exception Handler - Server Error"));
//	}
	
	

}
