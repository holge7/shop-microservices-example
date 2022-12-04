package com.shop.auth.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shop.auth.exception.UserNotFoundException;
import com.shop.auth.model.Rol;
import com.shop.auth.model.User;
import com.shop.auth.repository.UserRepository;

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
