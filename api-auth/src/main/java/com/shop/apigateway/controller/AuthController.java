package com.shop.apigateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.apigateway.model.Roles;
import com.shop.apigateway.model.User;
import com.shop.apigateway.service.UserService;
import com.shop.apigateway.util.ApiResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private final UserService userService;
	
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/test")
	public ResponseEntity<String> test() {
		return new ResponseEntity<>(
					"Test passed :)",
					HttpStatus.OK
				);
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse> register(
			@RequestBody User user
			) throws Exception {
			return userService.register(user, Roles.USER);
	}
	
}
