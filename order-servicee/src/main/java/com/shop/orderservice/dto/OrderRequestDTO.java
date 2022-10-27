package com.shop.orderservice.dto;

import java.util.List;


public class OrderRequestDTO {

	public List<OrderLineItemsListDTO> orderLineItemsList;

	public OrderRequestDTO() {}
	
	public OrderRequestDTO(List<OrderLineItemsListDTO> orderLineItemsList) {
		this.orderLineItemsList = orderLineItemsList;
	}

	public List<OrderLineItemsListDTO> getOrderLineItemsList() {
		return orderLineItemsList;
	}

	public void setOrderLineItemsList(List<OrderLineItemsListDTO> orderLineItemsList) {
		this.orderLineItemsList = orderLineItemsList;
	}
	
	
	
}
