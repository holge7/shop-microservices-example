package com.shop.productservice.dto;

import java.math.BigDecimal;

public class ProductRequestDTO {
	public String name;
	public String description;
	public BigDecimal price;
	
	public ProductRequestDTO() {}
	
	public ProductRequestDTO(String name, String description, BigDecimal price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
	
	
}
