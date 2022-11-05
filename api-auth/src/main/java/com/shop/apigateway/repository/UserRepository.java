package com.shop.apigateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.apigateway.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	public boolean existsByEmail(String email);
	
}
