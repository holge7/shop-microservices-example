package com.shop.apigateway.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.shop.apigateway.exception.UserRegisterNotValidException;
/*
@Component
public class CustomAuthenticationManager implements AuthenticationManager {

	private UserDetailsServiceImpl customUserDetailsService;
	private PasswordEncoder passwordEncoder;
	
	public CustomAuthenticationManager (
			UserDetailsServiceImpl customUserDetailsService,
			PasswordEncoder passwordEncoder
			) {
		this.customUserDetailsService = customUserDetailsService;
		this.passwordEncoder = passwordEncoder;
	}
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		final UserDetailsImpl userDetails = customUserDetailsService.loadUserByEmail(authentication.getName());
		if (!passwordEncoder.matches(authentication.getCredentials().toString(), userDetails.getPassword())) {
			throw new UserRegisterNotValidException();
		}

		return new UsernamePasswordAuthenticationToken(
				userDetails, 
				userDetails.getPassword(), 
				userDetails.getAuthorities());
	}
	
}*/
