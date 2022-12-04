package com.shop.apigateway.dto.payload.response;

import java.util.List;

public class JwtResponse {

	public String token;
	public String type = "Bearer";
	public Long id;
	public String username;
	public String email;
	public List<String> roles;
	
	public JwtResponse() {}

	public JwtResponse(String token, Long id, String username, String email, List<String> roles) {
		this.token = token;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}
	
	public JwtResponse(String email, List<String> roles) {
		this.token = "token";
		this.id = 1L;
		this.username = "username";
		this.email = email;
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	protected void setUsername(String username) {
		this.username = username;
	}

	protected String getEmail() {
		return email;
	}

	protected void setEmail(String email) {
		this.email = email;
	}

	protected List<String> getRoles() {
		return roles;
	}

	protected void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "JwtResponse [token=" + token + ", type=" + type + ", id=" + id + ", username=" + username + ", email="
				+ email + ", roles=" + roles + "]";
	}

	
}
