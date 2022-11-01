package com.shop.inventoryservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.inventoryservice.dto.InventoryResponseDTO;
import com.shop.inventoryservice.service.InventoryService;
import com.shop.inventoryservice.util.ApiResponse;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
	
	private final InventoryService inventoryService;
	private final ObjectMapper mapper;
	
	public InventoryController(InventoryService inventoryService, ObjectMapper mapper) {
		this.inventoryService = inventoryService;
		this.mapper = mapper;
	}
	
	@GetMapping("")
	public ResponseEntity<ApiResponse> isInStockApi(@RequestParam List<String> skuCodes) {
		
		List<InventoryResponseDTO> isInStock = inventoryService.isInStock(skuCodes);		
		ApiResponse response = new ApiResponse(isInStock);
		
		return new ResponseEntity<ApiResponse>(
					response,
					HttpStatus.OK
				);
	}
	
	@GetMapping("/service")
	public List<InventoryResponseDTO> isInStock(@RequestParam List<String> skuCodes) {
		return inventoryService.isInStock(skuCodes);		
	}
	
}
