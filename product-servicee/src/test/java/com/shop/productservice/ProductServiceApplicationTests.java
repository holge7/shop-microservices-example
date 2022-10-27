package com.shop.productservice;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.productservice.dto.ProductRequestDTO;
import com.shop.productservice.model.repository.ProductRepository;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {
	
	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.2");
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private ProductRepository productRepository;
	
	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		//We need specifie dynamicly the url from mongo db in the testContainer (like have a own .properties for test i think)
		//TODO: comprobar que esto se puede poner en .properties
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}
	
	
	@Test
	void shouldCreateProduct() throws Exception {
		
		ProductRequestDTO productRequest = getProductRequest();
		
		String productRequestString = objectMapper.writeValueAsString(productRequest);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
					.contentType(MediaType.APPLICATION_JSON)
					.content(productRequestString))
		.andExpect(status().isCreated());
		
		Assertions.assertTrue(productRepository.findAll().size() == 1);
	}	
	
	//TODO: test for get route
	
	private ProductRequestDTO getProductRequest() {
		ProductRequestDTO productRequest = new ProductRequestDTO();
		productRequest.setName("iPhone 13");
		productRequest.setDescription("Another more....");
		productRequest.setPrice(BigDecimal.valueOf(1200));
		
		return productRequest;
	}

}







