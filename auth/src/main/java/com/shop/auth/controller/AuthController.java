package com.shop.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.auth.dto.payload.request.LoginRequest;
import com.shop.auth.dto.payload.request.SignupRequest;
import com.shop.auth.service.UserService;
import com.shop.auth.util.ApiResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	UserService userService;
	
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/test")
	public ResponseEntity<ApiResponse> test(){
		
		ApiResponse response = new ApiResponse(0, "Hello");
		
		return new ResponseEntity<ApiResponse>(
				response,
				HttpStatus.OK
			);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse> login(
			@RequestBody LoginRequest loginRequest
			) throws Exception {
		return userService.login(loginRequest);
	}
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse> register(
			@RequestBody SignupRequest singUpRequest
			) throws Exception {
		return userService.register(singUpRequest);
	}
	
	
}
