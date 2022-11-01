package com.shop.orderservice.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.orderservice.dto.InventoryResponseDTO;
import com.shop.orderservice.dto.OrderLineItemsListDTO;
import com.shop.orderservice.dto.OrderRequestDTO;
import com.shop.orderservice.model.Order;
import com.shop.orderservice.model.OrderLineItems;
import com.shop.orderservice.repository.OrderRepository;
import com.shop.orderservice.util.ApiResponse;

import net.bytebuddy.asm.Advice.This;

import com.shop.orderservice.mapper.Mapper;
import com.shop.orderservice.mapper.OrderMapper;

@Service
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final WebClient.Builder webClientBuilder;
	OrderMapper orderMapper;
	private final ObjectMapper mapper;
	
	public OrderService(OrderMapper orderMapper, OrderRepository orderRepository, WebClient.Builder webClientBuilder, ObjectMapper mapper) {
		this.orderMapper = orderMapper;
		this.orderRepository = orderRepository;
		this.webClientBuilder = webClientBuilder;
		this.mapper = mapper;
	}
	
	public List<OrderLineItemsListDTO> placeOrder(OrderRequestDTO orderRequestDTO) {
		Order order = new Order();

		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderLineItems> orderLineItems = orderRequestDTO.getOrderLineItemsList()
			.stream()
			.map(orderLindeItemsDTO -> orderMapper.orderLineItems(orderLindeItemsDTO))
			.toList();
		
		order.setOrderLineItemsList(orderLineItems);
		
		List<String> skuCodes = order.getOrderLineItemsList().stream()
			.map(OrderLineItems::getSkuCode)
			.toList();
		
		// Call Inventory service, and place order if product is in stock
		InventoryResponseDTO[] inventoryResposne = webClientBuilder.build()
			.get()
			.uri("http://localhost:8082/api/inventory/service",
					uriBuilder -> uriBuilder.queryParam("skuCodes", skuCodes).build()
				)
			.retrieve()
			.bodyToMono(InventoryResponseDTO[].class)
			.block();
		
		boolean allProductsIsInStock = Arrays.stream(inventoryResposne)
				.allMatch(inventoryResponse -> inventoryResponse.isInStock() == true);
		
		if (allProductsIsInStock) {
			orderRepository.save(order);
		}else {
			throw new IllegalArgumentException("Product is not in stock, please try again later");
		}
		
		
		return orderLineItems.stream()
				.map(orderLineItem -> orderMapper.orderLineItemsDTO(orderLineItem))
				.toList();
	}

	
	
}
