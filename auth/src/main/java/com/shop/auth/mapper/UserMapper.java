package com.shop.auth.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.shop.auth.dto.UserDTO;
import com.shop.auth.dto.UserRegisterDTO;
import com.shop.auth.model.User;

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
