package com.shop.apigateway.security;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import com.shop.apigateway.config.JwtParseResponseDto;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {
	
    private final RestTemplate restTemplate;
    private static final String JWT_PARSE_URL = "http://auth-service/v1/jwt/parse";
    public AuthTokenFilter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			  System.out.println("=================================");
			  System.out.println("Paso por doFilterInternal");
			  System.out.println("=================================");
			String jwt = parseJwt(request);
			//TODO: if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
			if (jwt != null) {
			
				//Get email from jwt
				JwtParseResponseDto responseDto = parseJwt(jwt);
				
				//Load user assocated to the token
				//UserDetails userDetails = userDetailsService.loadUserByEmail(email);
				UsernamePasswordAuthenticationToken authentication = 
						new UsernamePasswordAuthenticationToken(
								responseDto.getEmail(),
								null,
								responseDto.getAuthorities().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				//Save user in securityContext
				SecurityContextHolder.getContext().setAuthentication(authentication);
				logger.info(String.format("User [%s] access to [%s]", responseDto.getEmail(), request.getRequestURI()));
				
			}
		} catch (Exception e) {
			logger.error("Cannot set user authentication: {}", e);
		}
		
		filterChain.doFilter(request, response);
	}
	
	private String parseJwt(HttpServletRequest request) {
		// Get header for auth
		String headerAuth = request.getHeader("Authorization");
		
		// Check if it have text and is of type Bearer
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length()); // substring of 7 becouse it is "|B|e|a|r|e|r| | ......."
		}
		
		return null;
	}
	
    private JwtParseResponseDto parseJwt(String token) {
        JwtParseResponseDto responseDto = restTemplate.postForObject(JWT_PARSE_URL, new JwtParseRequestDto(token),
                JwtParseResponseDto.class);

        Objects.requireNonNull(responseDto);
        return responseDto;
    }

}
