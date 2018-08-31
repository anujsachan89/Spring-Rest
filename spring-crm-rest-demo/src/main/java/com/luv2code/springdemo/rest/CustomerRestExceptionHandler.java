package com.luv2code.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {
	// add an exception handler for customernotfound
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exec){
		// create CustomerErrorRespnse
		CustomerErrorResponse error = new CustomerErrorResponse(HttpStatus.NOT_FOUND.value(),exec.getMessage(),System.currentTimeMillis());
		//return response entity
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	// add an exception handler to all exceptions
	public ResponseEntity<CustomerErrorResponse> handleException(Exception exec){
		// create CustomerErrorRespnse
		CustomerErrorResponse error = new CustomerErrorResponse(HttpStatus.BAD_REQUEST.value(),exec.getMessage(),System.currentTimeMillis());
		//return response entity
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
}
