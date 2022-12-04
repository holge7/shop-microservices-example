package com.shop.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.auth.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	public boolean existsByEmail(String email);

	public Optional<User> findByEmail(String email);
	
}
