package com.shop.inventoryservice.dto;

public class InventoryResponseDTO {
	
	public String skuCode;
	public boolean inStock;
	
	public InventoryResponseDTO() {}

	public InventoryResponseDTO(String skuCode, boolean inStock) {
		this.skuCode = skuCode;
		this.inStock = inStock;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}
	
}
