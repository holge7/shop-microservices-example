package com.shop.inventoryservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.inventoryservice.dto.InventoryResponseDTO;
import com.shop.inventoryservice.mapper.InventoryMapper;
import com.shop.inventoryservice.model.Inventory;
import com.shop.inventoryservice.repository.InventoryRepository;

import net.bytebuddy.asm.Advice.Return;

@Service
public class InventoryService {

	private InventoryRepository inventoryRepository;
	private InventoryMapper inventoryMapper;
	private ObjectMapper mapper;
	
	public InventoryService(InventoryRepository inventoryRepository, InventoryMapper inventoryMapper, ObjectMapper mapper) {
		this.inventoryRepository = inventoryRepository;
		this.inventoryMapper = inventoryMapper;
		this.mapper = mapper;
	}
	
	@Transactional(readOnly = true)
	public List<InventoryResponseDTO> isInStock(List<String> skuCodes) {
		
		return inventoryRepository.findBySkuCodeIn(skuCodes)
				.stream()
				.map(inventoryMapper::inventoryResponseDTO)
				.toList();

	}
}
