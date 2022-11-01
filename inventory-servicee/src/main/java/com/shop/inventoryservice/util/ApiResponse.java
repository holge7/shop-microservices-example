package com.shop.inventoryservice.util;

public class ApiResponse {
	public long code;
	public Object data;
	public String message;
	
	public ApiResponse() {}
	
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

	public long getCode() {
		return code;
	}


	public void setCode(long code) {
		this.code = code;
	}


	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}

}