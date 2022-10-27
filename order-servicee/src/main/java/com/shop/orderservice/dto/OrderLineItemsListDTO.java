package com.shop.orderservice.dto;

import java.math.BigDecimal;

public class OrderLineItemsListDTO {
	
	public String skuCode;
	public BigDecimal price;
	public Integer quantity;
	
	
	public OrderLineItemsListDTO() {}
	
	public OrderLineItemsListDTO(String skuCode, BigDecimal price, Integer quantity) {
		this.skuCode = skuCode;
		this.price = price;
		this.quantity = quantity;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
