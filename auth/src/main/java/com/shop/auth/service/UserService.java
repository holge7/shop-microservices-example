package com.shop.auth.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shop.auth.dto.payload.request.LoginRequest;
import com.shop.auth.dto.payload.responose.JwtResponse;
//import com.shop.auth.security.UserDetailsImpl;
import com.shop.auth.dto.UserDTO;
import com.shop.auth.security.jwt.JwtUtils;
import com.shop.auth.exception.RolNotFoundException;
import com.shop.auth.exception.UserAlreadyExistsException;
import com.shop.auth.exception.UserNotFoundException;
import com.shop.auth.mapper.UserMapper;
import com.shop.auth.model.ERol;
import com.shop.auth.model.Rol;
import com.shop.auth.model.User;
import com.shop.auth.repository.RolRepository;
import com.shop.auth.repository.UserRepository;
import com.shop.auth.security.UserDetailsImpl;
import com.shop.auth.dto.payload.request.SignupRequest;
import com.shop.auth.util.ApiResponse;

@Service
public class UserService {
	
	UserRepository userRepository;
	RolRepository rolRepository;
	PasswordEncoder passwordEncoder;
	UserMapper userMapper;
	AuthenticationManager authenticationManager;
	JwtUtils jwtUtils;
	
	public UserService(UserRepository userRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder, UserMapper userMapper, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
		this.userRepository = userRepository;
		this.rolRepository = rolRepository;
		this.passwordEncoder = passwordEncoder;
		this.userMapper = userMapper;
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
	}
	

	public ResponseEntity<ApiResponse> login(LoginRequest userLogin){

		//Validate the existence of the user/password
		if (!userRepository.existsByEmail(userLogin.getEmail())) {
			throw new UserNotFoundException(userLogin.getEmail());
		}

		
		//Authenticate user
		Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword())
				);
		
		//Set it in context
		//SecurityContextHolder.getContext().setAuthentication(authentication);
		
		//Generate jwt
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		//Get user details
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
	    List<String> roles = userDetails.getAuthorities().stream()
	            .map(item -> item.getAuthority())
	            .collect(Collectors.toList());
		
	    //Return data
		JwtResponse jwtResponse = new JwtResponse(
					jwt,
					userDetails.getID(),
					userDetails.getUsername(),
					userDetails.getEmail(),
					roles					
				);
		
		ApiResponse response = new ApiResponse(jwtResponse);
		
		return new ResponseEntity<ApiResponse>(
					response,
					HttpStatus.OK
				);

	}
	
	public ResponseEntity<ApiResponse> register(SignupRequest userRequest) {
		//Check if exists user
				if (userRepository.existsByEmail(userRequest.getEmail())) {
					throw new UserAlreadyExistsException(userRequest.getEmail());
				}
				
				//Create user
				User user = new User(
						userRequest.getEmail(),
						userRequest.getName(),
						passwordEncoder.encode(userRequest.getPassword())
						);
				
				//Assign roles
				Set<String> strRole = userRequest.getRole();
				Set<Rol> roles = new HashSet<>();
				if (strRole == null) {
					Rol userRole = rolRepository.findByRol(ERol.ROLE_USER)
							.orElseThrow(() -> new RolNotFoundException(ERol.ROLE_USER));
					
					roles.add(userRole);

				}else {
					strRole.forEach(role -> {
						Rol userRole;
						switch (role) {
						case "ROLE_ADMIN": 
							userRole = rolRepository.findByRol(ERol.ROLE_ADMIN)
							.orElseThrow(() -> new RolNotFoundException(ERol.ROLE_ADMIN));
							
							roles.add(userRole);
							break;
							
						case "ROLE_MODERATOR": 
							userRole = rolRepository.findByRol(ERol.ROLE_MODERATOR)
							.orElseThrow(() -> new RolNotFoundException(ERol.ROLE_MODERATOR));
							
							roles.add(userRole);
							break;
							
						case "ROLE_USER": 
							userRole = rolRepository.findByRol(ERol.ROLE_USER)
							.orElseThrow(() -> new RolNotFoundException(ERol.ROLE_USER));
							
							roles.add(userRole);
							break;
							
						default:
							throw new RolNotFoundException(role);
						}
					});

				}
				
				user.setRol(roles);

				//Save and transform user
				UserDTO userDTO = userMapper.userDTO(userRepository.save(user));
				userDTO.setRoles(roles);
				
				//Return ResponseEntity
				ApiResponse response = new ApiResponse(userDTO);
				return new ResponseEntity<ApiResponse>(
							response,
							HttpStatus.CREATED
						);
	}
	
	public User getUserEntity(String email){
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException(email));

		return user;
	}
	
	public boolean existsUser(String email) {
		return userRepository.existsByEmail(email);
	}
	
}
