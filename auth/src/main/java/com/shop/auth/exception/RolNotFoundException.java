package com.shop.auth.exception;

import org.springframework.http.HttpStatus;

import com.shop.auth.model.ERol;

public class RolNotFoundException extends RolException {

	private static final long serialVersionUID = 1L;
	private static String msgString = RESOURCE_NAME+" [%s] not found.";
	private static HttpStatus httpStatus = HttpStatus.NOT_FOUND;
	
	public RolNotFoundException(ERol rol) {
		super(msg(rol), httpStatus);
	}
	
	public RolNotFoundException(String rol) {
		super(String.format(msgString, rol), httpStatus);	
	}

	private static String msg(ERol rol) {
		return String.format(msgString, rol.toString());		
	}
	

}
