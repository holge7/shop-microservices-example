package com.shop.apigateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.apigateway.util.ApiResponse;

@RestController
@RequestMapping("/api/test")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TestController {
	
	@GetMapping("/all")
	public ResponseEntity<ApiResponse> allAccess(){
		ApiResponse response = new ApiResponse("Public content");
		return new ResponseEntity<ApiResponse>(
					response,
					HttpStatus.OK
				);
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<ApiResponse> userAccess(){
		ApiResponse response = new ApiResponse("User content");
		return new ResponseEntity<ApiResponse>(
					response,
					HttpStatus.OK
				);
	}
	
	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<ApiResponse> modAccess(){
		ApiResponse response = new ApiResponse("Moderator content");
		return new ResponseEntity<ApiResponse>(
					response,
					HttpStatus.OK
				);
	}
	
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponse> adminAccess(){
		ApiResponse response = new ApiResponse("Admin content");
		return new ResponseEntity<ApiResponse>(
					response,
					HttpStatus.OK
				);
	}
	
	
}
