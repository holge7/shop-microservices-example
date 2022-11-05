package com.shop.apigateway.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.shop.apigateway.util.ApiResponse;

@ControllerAdvice
public class ExceptionHandlerImpl extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(RolException.class)
	public ResponseEntity<ApiResponse> handleRolException(RolException RolException){
		
		ApiResponse response = new ApiResponse(1, RolException.getMsg());

		return new ResponseEntity<ApiResponse>(
					response,
					RolException.httpStatus
				);
	}
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ApiResponse> handleUserException(UserException userException){
		
		ApiResponse response = new ApiResponse(1, userException.getMsg());
		
		return new ResponseEntity<ApiResponse>(
					response,
					userException.httpStatus
				);
	}
	
}