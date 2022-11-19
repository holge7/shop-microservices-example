package com.shop.apigateway.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.apigateway.model.Rol;
import com.shop.apigateway.model.ERol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

	public Optional<Rol> findByRol(ERol rol);


}
