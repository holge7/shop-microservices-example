package com.shop.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.shop.orderservice.dto.OrderLineItemsListDTO;
import com.shop.orderservice.dto.OrderRequestDTO;
import com.shop.orderservice.model.Order;
import com.shop.orderservice.model.OrderLineItems;
import com.shop.orderservice.repository.OrderRepository;
import com.shop.orderservice.mapper.OrderMapper;

@Service
public class OrderService {
	
	private final OrderRepository orderRepository;
	OrderMapper orderMapper;
	
	public OrderService(OrderMapper orderMapper, OrderRepository orderRepository) {
		this.orderMapper = orderMapper;
		this.orderRepository = orderRepository;
	}
	
	public List<OrderLineItemsListDTO> placeOrder(OrderRequestDTO orderRequestDTO) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderLineItems> orderLineItems = orderRequestDTO.getOrderLineItemsList()
			.stream()
			.map(orderLindeItemsDTO -> orderMapper.orderLineItems(orderLindeItemsDTO))
			.toList();
		
		order.setOrderLineItemsList(orderLineItems);
		
		orderRepository.save(order);
		
		return orderLineItems.stream()
				.map(orderLineItem -> orderMapper.orderLineItemsDTO(orderLineItem))
				.toList();
	}

	
	
}
