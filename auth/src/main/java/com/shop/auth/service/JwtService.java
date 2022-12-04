package com.shop.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shop.auth.dto.UserDTO;
import com.shop.auth.dto.payload.responose.JwtResponse;
import com.shop.auth.dto.payload.responose.jwtResponsee;
import com.shop.auth.security.jwt.JwtUtils;
import com.shop.auth.util.ApiResponse;

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
    
    public ResponseEntity<ApiResponse> parseJwt(String token) {
    	System.out.println("Que pasa banda");
    	System.out.println(token);
    	Claims claims = Jwts.parser()
				.setSigningKey(jwtSecret)
				.parseClaimsJws(token)
				.getBody();
		System.out.println("paso por aqui");
		System.out.println(claims);
		String email = claims.getSubject();
		List<String> authorities = claims.get("authorities", List.class);
		jwtResponsee jwtResponose = new jwtResponsee(email, authorities);
		//return new JwtResponse(email, authorities);
		ApiResponse response = new ApiResponse(jwtResponose);
		return new ResponseEntity<ApiResponse>(
					response,
					HttpStatus.OK
				);
    }
}
