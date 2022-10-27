package com.shop.orderservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.orderservice.dto.OrderLineItemsListDTO;
import com.shop.orderservice.dto.OrderRequestDTO;
import com.shop.orderservice.service.OrderService;
import com.shop.orderservice.util.ApiResponse;

@RestController
@RequestMapping("/api/order")
public class OrderContoller {
	
	private final OrderService orderService;
	
	public OrderContoller(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@PostMapping("")
	public ResponseEntity<ApiResponse> placeOrder(@RequestBody OrderRequestDTO orderRequest) {
		List<OrderLineItemsListDTO> orderLineItemsDTO = orderService.placeOrder(orderRequest);
		
		ApiResponse response = new ApiResponse(orderLineItemsDTO);
		
		return new ResponseEntity<ApiResponse>(
					response, 
					HttpStatus.CREATED
				);
	}
}
