package com.shop.apigateway.exception;

import org.springframework.http.HttpStatus;

import com.shop.apigateway.model.User;

public class UserNotFoundException extends UserException {

	private static final long serialVersionUID = 1L;
	private static String msgString = RESOURCE_NAME+" [%s] not found.";
	private static HttpStatus httpStatus = HttpStatus.NOT_FOUND;

	public UserNotFoundException(User user) {
		super(msg(user), httpStatus);
	}
	
	private static String msg(User user) {
		return String.format(msgString, user.getEmail());		
	}

}
