package com.shop.inventoryservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.inventoryservice.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long>{
	
	Optional<Inventory> findBySkuCode(String skuCode); //jpa generate this query automatic

	List<Inventory> findBySkuCodeIn(List<String> skuCodes);
	
}
