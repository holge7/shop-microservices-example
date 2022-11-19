package com.shop.apigateway;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.apigateway.dto.UserDTO;
import com.shop.apigateway.dto.UserRegisterDTO;
import com.shop.apigateway.mapper.UserMapper;
import com.shop.apigateway.model.Rol;
import com.shop.apigateway.model.ERol;
import com.shop.apigateway.model.User;
import com.shop.apigateway.repository.RolRepository;
import com.shop.apigateway.repository.UserRepository;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class AuthControllerTests {
	
	@Container
	private static final MySQLContainer mysqlContainer = new MySQLContainer("mysql:latest")
	.withDatabaseName("auth-service")
	.withUsername("root")
	.withPassword("root");
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RolRepository rolRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
	}
	
	@BeforeEach
	public void tearUpClass() throws Exception {
		Rol admin = new Rol();
		admin.setId(1);
		admin.setRol(ERol.ROLE_ADMIN);
	
		Rol user = new Rol();
		user.setId(2);
		user.setRol(ERol.ROLE_USER);
		rolRepository.save(admin);
		rolRepository.save(user);
	}
	
	@Test
	public void test() throws Exception {
		String userRequeString = objectMapper.writeValueAsString(getUser());
		
		System.out.println(userRequeString);
		
		ResultActions resultActions = mockMvc.perform(
			MockMvcRequestBuilders.post("/api/auth/register")
			.contentType(MediaType.APPLICATION_JSON)
			.content(userRequeString));
		
		System.out.println(resultActions);
		
		resultActions.andExpect(status().isCreated());
	}
	
	private UserRegisterDTO getUser() {
		UserRegisterDTO user = new UserRegisterDTO();
		user.setEmail("jorgeeeee@gmail.com");
		user.setName("Jorge");
		user.setPassword("password");
		
		return user;
	}
	
	
}
