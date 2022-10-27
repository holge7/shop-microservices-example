package com.shop.inventoryservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.inventoryservice.service.InventoryService;
import com.shop.inventoryservice.util.ApiResponse;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
	
	private final InventoryService inventoryService;
	
	public InventoryController(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}
	
	@GetMapping("/{skuCode}")
	public ResponseEntity<ApiResponse> isInStock(@PathVariable String skuCode) {
		Boolean isInStock = inventoryService.isInStock(skuCode);
		ApiResponse response = new ApiResponse(isInStock);
		
		return new ResponseEntity<ApiResponse>(
					response,
					HttpStatus.OK
				);
	}
}
