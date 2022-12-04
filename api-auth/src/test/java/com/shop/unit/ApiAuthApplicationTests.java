package com.shop.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.UserRequestPostProcessor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shop.apigateway.dto.payload.request.LoginRequest;
import com.shop.apigateway.exception.UserException;
import com.shop.apigateway.mapper.UserMapper;
import com.shop.apigateway.model.ERol;
import com.shop.apigateway.model.Rol;
import com.shop.apigateway.model.User;
import com.shop.apigateway.repository.RolRepository;
import com.shop.apigateway.repository.UserRepository;
import com.shop.apigateway.security.jwt.JwtUtils;
import com.shop.apigateway.service.UserService;
import com.shop.apigateway.util.ApiResponse;

//@SpringBootTest
@ContextConfiguration(classes = { AuthenticationManager.class })
@RunWith(SpringJUnit4ClassRunner.class)
class ApiAuthApplicationTests {
	
	private UserRepository userRepository; //mock
	private RolRepository rolRepository; //mock
	
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserMapper userMapper;	
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtUtils jwtUtils;
	
	
	private UserService userService;
	
	@BeforeEach
	void setupService() {
		userRepository = mock(UserRepository.class);
		rolRepository = mock(RolRepository.class);
		userService = new UserService(userRepository, passwordEncoder, userMapper, rolRepository, authenticationManager, jwtUtils);
	}
	
	
	@Test
	public void login_user() {
		
		String email = "jorge@gmail.com";
		String password = "password";
		Set<Rol> roles = new HashSet<>();
		roles.add(new Rol(ERol.ROLE_USER));

		when(userRepository.existsByEmail(email)).thenReturn(true);
		
		// Save user
		User user = new User(email, "Jorge", password, roles);
		//User userSave = userRepository.save(user);
		
		// Loggin user
		LoginRequest loginRequest = new LoginRequest(email, password);
		ResponseEntity<ApiResponse> response = userService.login(loginRequest);
		
		assertThat(response.status(HttpStatus.OK));
	}
	
	/*@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;

	@Test
	void login_user() {
		
		//when(userRepository)
		
		String email = "jorge@gmail.com";
		String password = "password";
		Set<Rol> roles = new HashSet<>();
		roles.add(new Rol(ERol.ROLE_USER));
		
		// Save user
		User user = new User(email, "Jorge", password, roles);
		//User userSave = userRepository.save(user);
		
		// Loggin user
		LoginRequest loginRequest = new LoginRequest(email, password);
		ResponseEntity<ApiResponse> response = userService.login(loginRequest);
		
		assertThat(response.status(HttpStatus.OK));
		
	}*/

}
