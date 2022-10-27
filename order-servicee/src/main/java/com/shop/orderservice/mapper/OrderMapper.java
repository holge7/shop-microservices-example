package com.shop.orderservice.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.shop.orderservice.dto.OrderLineItemsListDTO;
import com.shop.orderservice.model.OrderLineItems;

@Service
public class OrderMapper {
	
	private ModelMapper modelMapper;
	
	public OrderMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public OrderLineItems orderLineItems(OrderLineItemsListDTO orderLineItemsDTO) {
		return modelMapper.map(orderLineItemsDTO, OrderLineItems.class);
	}
	
	public OrderLineItemsListDTO orderLineItemsDTO(OrderLineItems orderLineItems) {
		return modelMapper.map(orderLineItems, OrderLineItemsListDTO.class);
	}
	
}
