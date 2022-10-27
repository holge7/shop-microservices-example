package com.shop.productservice.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.shop.productservice.dto.ProductRequestDTO;
import com.shop.productservice.dto.ProductResponseDTO;
import com.shop.productservice.model.Product;

@Service
public class ProductMapper {
	
	private ModelMapper modelMapper;
	
	public ProductMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public Product Product(ProductRequestDTO productRequestDTO) {
		return modelMapper.map(productRequestDTO, Product.class);
	}
	
	public ProductRequestDTO mapProductRequestDTO(Product product) {
		return modelMapper.map(product, ProductRequestDTO.class);
	}
	
	public ProductResponseDTO mapProductResponseDTO(Product product) {
		return modelMapper.map(product, ProductResponseDTO.class);
	}
}
