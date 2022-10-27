package com.shop.orderservice.dto;

import java.util.List;

import com.shop.orderservice.model.OrderLineItems;

public class OrderResponseDTO {
	
	public String orderNumber;
	public List<OrderLineItems> orderLineItemsList;
	
	public OrderResponseDTO() {}
	
	public OrderResponseDTO(String orderNumber, List<OrderLineItems> orderLineItemsList) {
		this.orderNumber = orderNumber;
		this.orderLineItemsList = orderLineItemsList;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public List<OrderLineItems> getOrderLineItemsList() {
		return orderLineItemsList;
	}

	public void setOrderLineItemsList(List<OrderLineItems> orderLineItemsList) {
		this.orderLineItemsList = orderLineItemsList;
	}
	
}
