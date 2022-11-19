package com.shop.apigateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.apigateway.dto.payload.request.LoginRequest;
import com.shop.apigateway.dto.payload.request.SignupRequest;
import com.shop.apigateway.service.UserService;
import com.shop.apigateway.util.ApiResponse;



@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private final UserService userService;
	
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse> login(
			@RequestBody LoginRequest user
			){
		return userService.login(user);
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse> register(
			@RequestBody SignupRequest singUpRequest
			) throws Exception {
		return userService.register(singUpRequest);
	}
	
}
