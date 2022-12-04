package com.shop.apigateway.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.shop.apigateway.dto.payload.response.JwtResponse;
import com.shop.apigateway.security.jwt.JwtUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtService {
	
	@Value("${security.app.jwtSecret}")
	private String jwtSecret;
	
    final JwtUtils jwtUtils;
    
    public JwtService(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }
    
    public JwtResponse parseJwt(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(jwtSecret)
				.parseClaimsJws(token)
				.getBody();
		
		String email = claims.getSubject();
		List<String> authorities = claims.get("authorities", List.class);
		
		return new JwtResponse(email, authorities);
		
    }
}
