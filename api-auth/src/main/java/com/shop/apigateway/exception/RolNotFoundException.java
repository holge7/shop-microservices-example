package com.shop.apigateway.exception;

import org.springframework.http.HttpStatus;

import com.shop.apigateway.model.Roles;

public class RolNotFoundException extends RolException {

	private static final long serialVersionUID = 1L;
	private static String msgString = RESOURCE_NAME+" [%s] not found.";
	private static HttpStatus httpStatus = HttpStatus.NOT_FOUND;
	
	public RolNotFoundException(Roles rol) {
		super(msg(rol), httpStatus);
	}
	
	private static String msg(Roles rol) {
		return String.format(msgString, rol.toString());		
	}

}
