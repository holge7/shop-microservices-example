package com.shop.productservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shop.productservice.dto.ProductRequestDTO;
import com.shop.productservice.dto.ProductResponseDTO;
import com.shop.productservice.mapper.ProductMapper;
import com.shop.productservice.model.Product;
import com.shop.productservice.model.repository.ProductRepository;

@Service
public class ProductService {
	
	private final ProductRepository productRepository;
	private ProductMapper productMapper;
	
	Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
		this.productRepository = productRepository;
		this.productMapper = productMapper;
	}
	
	public ProductRequestDTO createProduct(ProductRequestDTO productRequestDTO) {
		Product product = new Product();
		product.setName(productRequestDTO.getName());
		product.setDescription(productRequestDTO.getDescription());
		product.setPrice(productRequestDTO.getPrice());
		
		ProductRequestDTO newProductDTO = productMapper.mapProductRequestDTO(productRepository.save(product));
	
		logger.info(String.format("Product %d is saved", product.getId()));
		return newProductDTO;
	}
	
	
	public List<ProductResponseDTO> getAllProducts(){
		return productRepository.findAll().stream()
			.map(product -> productMapper.mapProductResponseDTO(product))
			.toList();
		
	}
	
	
}
