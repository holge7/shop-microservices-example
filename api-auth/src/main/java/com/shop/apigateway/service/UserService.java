package com.shop.apigateway.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.EntityResponse;

import com.shop.apigateway.dto.UserDTO;
import com.shop.apigateway.exception.UserAlreadyExistsException;
import com.shop.apigateway.mapper.UserMapper;
import com.shop.apigateway.model.Rol;
import com.shop.apigateway.model.Roles;
import com.shop.apigateway.model.User;
import com.shop.apigateway.repository.RolRepository;
import com.shop.apigateway.repository.RolServiceImpl;
import com.shop.apigateway.repository.UserRepository;
import com.shop.apigateway.util.ApiResponse;

@Service
public class UserService {
	
	private UserRepository userRepository;
	private RolServiceImpl rolServiceImpl;
	private PasswordEncoder passwordEncoder;
	private UserMapper userMapper;
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RolServiceImpl rolServiceImpl, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.rolServiceImpl = rolServiceImpl;
		this.userMapper = userMapper;
	}
	
	public ResponseEntity<ApiResponse> register(User user, Roles rol) throws Exception {
		//Check if exists user
		if (userRepository.existsByEmail(user.getEmail())) {
			throw new UserAlreadyExistsException(user);
		}
		
		
		//Create user
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		//Assign roles
		Rol rolToAssign = rolServiceImpl.findByRol(Roles.USER);
		user.setRol(Collections.singleton(rolToAssign));
		
		UserDTO userDTO = userMapper.userDTO(userRepository.save(user));
		
		ApiResponse response = new ApiResponse(userDTO);
		return new ResponseEntity<ApiResponse>(
					response,
					HttpStatus.CREATED
				);
	}

	
	
}
