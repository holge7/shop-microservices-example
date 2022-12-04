package com.shop.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.auth.controller.JwtController;
import com.shop.auth.dto.payload.responose.JwtResponse;
import com.shop.auth.service.JwtService;
import com.shop.auth.util.ApiResponse;

@RestController
@RequestMapping("/api/jwt")
public class JwtController {
    private final Logger log = LoggerFactory.getLogger(JwtController.class);

    final JwtService jwtService;

    public JwtController(JwtService jwtService) {
        this.jwtService = jwtService;
    }
    
    @PostMapping("/parse")
    public ResponseEntity<ApiResponse> parseJwt(
    		@RequestBody String token
    		){
    	return jwtService.parseJwt(token);
    }

}
