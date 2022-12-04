package com.shop.auth.dto.payload.responose;

import java.util.List;

public class jwtResponsee {
	public String email;
	public List<String> authorities;
	
	public jwtResponsee(String email, List<String> authorities) {
		this.email = email;
		this.authorities = authorities;
	}

	protected String getEmail() {
		return email;
	}
	protected void setEmail(String email) {
		this.email = email;
	}
	protected List<String> getAuthorities() {
		return authorities;
	}
	protected void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
	
	
}
