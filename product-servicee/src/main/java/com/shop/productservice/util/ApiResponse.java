package com.shop.productservice.util;

public class ApiResponse {
	public long code;
	public Object data;
	public String message;
	
	
	public ApiResponse(Object data) {
		this.code = 0;
		this.data = data;
		this.message = "";
	}
	
	public ApiResponse(long code, String message) {
		this.code = code;
		this.data = null;
		this.message = message;
	}
	
}
