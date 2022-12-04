package com.shop.apigateway.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.catalina.mapper.Mapper;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.door.auth.exception.RolNotFoundException;
import com.door.auth.exception.UserAlreadyExistsException;
import com.shop.apigateway.dto.UserDTO;
import com.shop.apigateway.dto.payload.request.LoginRequest;
import com.shop.apigateway.dto.payload.request.SignupRequest;
import com.shop.apigateway.dto.payload.response.JwtResponse;
import com.shop.apigateway.exception.UserNotFoundException;
import com.shop.apigateway.mapper.UserMapper;
import com.shop.apigateway.model.Rol;
import com.shop.apigateway.model.ERol;
import com.shop.apigateway.model.User;
import com.shop.apigateway.repository.RolRepository;
import com.shop.apigateway.repository.UserRepository;
import com.shop.apigateway.security.UserDetailsImpl;
import com.shop.apigateway.security.jwt.JwtUtils;
import com.shop.apigateway.util.ApiResponse;

@Service
public class UserService {
	
	private UserRepository userRepository;
	private RolRepository rolRepository;
	private PasswordEncoder passwordEncoder;
	private UserMapper userMapper;
	private AuthenticationManager authenticationManager;
	private JwtUtils jwtUtils;
	
	public UserService(
			UserRepository userRepository, 
			PasswordEncoder passwordEncoder,  
			UserMapper userMapper,
			RolRepository rolRepository,
			AuthenticationManager authenticationManager,
			JwtUtils jwtUtils) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.userMapper = userMapper;
		this.rolRepository = rolRepository;
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
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
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
	
	
	public ResponseEntity<ApiResponse> register(SignupRequest userRequest) throws Exception {
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
