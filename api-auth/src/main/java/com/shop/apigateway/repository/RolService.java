package com.shop.apigateway.repository;

import com.shop.apigateway.model.Rol;
import com.shop.apigateway.model.Roles;

public interface RolService {
	
	public Rol findByRol(Roles rol) throws Exception;
	
}
