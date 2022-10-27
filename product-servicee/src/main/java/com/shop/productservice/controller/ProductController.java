package com.shop.productservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.productservice.dto.ProductRequestDTO;
import com.shop.productservice.dto.ProductResponseDTO;
import com.shop.productservice.service.ProductService;
import com.shop.productservice.util.ApiResponse;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@PostMapping("")
	public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductRequestDTO productRequest) {
		ProductRequestDTO productRequestDTO = productService.createProduct(productRequest);
		
		ApiResponse response = new ApiResponse(productRequestDTO);
		
		return new ResponseEntity<ApiResponse>(
					response,
					HttpStatus.CREATED
				);
	}
	
	@GetMapping("")
	public ResponseEntity<ApiResponse> getAllProduct() {
		List<ProductResponseDTO> products = productService.getAllProducts();
		
		ApiResponse response = new ApiResponse(products);
		
		return new ResponseEntity<ApiResponse>(
					response,
					HttpStatus.OK
				);
	}
	
	
	
}
