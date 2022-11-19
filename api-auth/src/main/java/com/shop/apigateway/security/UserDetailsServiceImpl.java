package com.shop.apigateway.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shop.apigateway.exception.UserNotFoundException;
import com.shop.apigateway.model.Rol;
import com.shop.apigateway.model.User;
import com.shop.apigateway.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	UserRepository userRepository;
	
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		/*User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException(email));
		
		return new org.springframework.security.core.userdetails.User(
				email, 
				user.getPassword(), 
				mapRol(user.getRol())
			);*/
		return null;
	}
	
	public UserDetailsImpl loadUserByEmail(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException(email));
		
		return UserDetailsImpl.build(user);
	}
	

}
