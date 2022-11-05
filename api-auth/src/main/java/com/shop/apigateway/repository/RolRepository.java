package com.shop.apigateway.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.common.base.Optional;
import com.shop.apigateway.model.Rol;
import com.shop.apigateway.model.Roles;

public interface RolRepository extends JpaRepository<Rol, Long> {

	public Rol findByRol(Roles rol);

	public Rol findByRol(String string);

}
