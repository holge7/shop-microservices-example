package com.shop.apigateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.apigateway.dto.payload.response.JwtResponse;
import com.shop.apigateway.service.JwtService;
import com.shop.apigateway.util.ApiResponse;

@RestController
@RequestMapping("/api/jwt")
public class JwtController {
    private final Logger log = LoggerFactory.getLogger(JwtController.class);

    final JwtService jwtService;

    public JwtController(JwtService jwtService) {
        this.jwtService = jwtService;
    }
    
    @PostMapping("/parse")
    public ResponseEntity<ApiResponse> parseJwt(@RequestBody JwtResponse requestDto){
    	
    	ApiResponse response = new ApiResponse(jwtService.parseJwt(null));
    	return new ResponseEntity<ApiResponse>(
    				response,
    				HttpStatus.OK
    			);
    }
    
}
