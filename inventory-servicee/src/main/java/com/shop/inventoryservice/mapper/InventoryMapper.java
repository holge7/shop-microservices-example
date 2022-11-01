package com.shop.inventoryservice.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.shop.inventoryservice.dto.InventoryResponseDTO;
import com.shop.inventoryservice.model.Inventory;

@Service
public class InventoryMapper {
	
	private final ModelMapper modelMapper;
	
	public InventoryMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public InventoryResponseDTO inventoryResponseDTO(Inventory inventory) {
		InventoryResponseDTO responseDTO = new InventoryResponseDTO();
		responseDTO.setSkuCode(inventory.getSkuCode());
		responseDTO.setInStock(inventory.getQuantity()>0);
		
		return responseDTO;
	}
	
}