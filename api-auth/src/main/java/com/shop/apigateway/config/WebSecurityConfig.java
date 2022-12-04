package com.shop.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.shop.apigateway.security.UserDetailsServiceImpl;
import com.shop.apigateway.security.jwt.AuthEntryPointJwt;
//import com.shop.apigateway.security.jwt.AuthTokenFilter;

@Configuration
public class WebSecurityConfig{
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	//@Autowired
	//private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}*/

	/*@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}*/
	
	  @Bean
	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//Disable spring-security csrf, because spring boot already has its own csrf, we
		//also allow all the get requests, but the rest we will apply basic authentication
	    http.cors().and().csrf().disable();
	    
	    return http.build();
	  }

}
