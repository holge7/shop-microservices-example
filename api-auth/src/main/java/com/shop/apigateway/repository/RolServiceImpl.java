package com.shop.apigateway.repository;

import org.springframework.stereotype.Service;

import com.shop.apigateway.exception.RolNotFoundException;
import com.shop.apigateway.model.Rol;
import com.shop.apigateway.model.Roles;

@Service
public class RolServiceImpl implements RolService{

	private RolRepository rolRepository;
	
	public RolServiceImpl(RolRepository rolRepository) {
		this.rolRepository = rolRepository;
	}
	
	@Override
	public Rol findByRol(Roles rolEnum) throws Exception {
		Rol rol = rolRepository.findByRol(rolEnum.toString());
		
		if (rol == null) throw new RolNotFoundException(rolEnum);
		
		return rol;
		
	}

}
