package com.shop.apigateway.dto.payload.request;

import java.util.Set;

public class SignupRequest {
	
	public String name;
	public String email;
	public Set<String> role;
	public String password;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Set<String> getRole() {
		return role;
	}
	
	public void setRole(Set<String> role) {
		this.role = role;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
