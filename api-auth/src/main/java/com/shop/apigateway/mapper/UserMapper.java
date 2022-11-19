package com.shop.apigateway.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.shop.apigateway.dto.UserDTO;
import com.shop.apigateway.dto.UserRegisterDTO;
import com.shop.apigateway.model.User;

@Component
public class UserMapper {
	
	private ModelMapper modelMapper;
	
	public UserMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public UserDTO userDTO(User user) {
		return modelMapper.map(user, UserDTO.class);
	}
	
	public UserRegisterDTO userRegisterDTO(User user) {
		return modelMapper.map(user, UserRegisterDTO.class);
	}
	
}
