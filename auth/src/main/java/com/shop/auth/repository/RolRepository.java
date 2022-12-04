package com.shop.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.auth.model.Rol;
import com.shop.auth.model.ERol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

	public Optional<Rol> findByRol(ERol rol);


}
