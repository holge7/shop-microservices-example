package com.shop.auth.dto;

import java.util.List;
import java.util.Set;

import com.shop.auth.model.Rol;

public class UserDTO {
	public String email;
	public String name;
	public Set<Rol> roles;
	
	public UserDTO() {}
	
	public UserDTO(String email, Set<Rol> authorities) {
		this.email = email;
		this.roles = authorities;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	
}
