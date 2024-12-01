package com.app.product.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.service.ProductService;

@SpringBootTest
class ProductServiceTest {
	@Autowired
	private ProductService productService;

	@Test
	void testFindProductByIdD() {
		float price = productService.findProductById(1).getPrice();
		assertEquals(true, price > 0, "Price is Zero/Negative for id 1");
	}
	
	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 3, 4, 5 })
	void testFindProductByIdE(int id) {
		float price = productService.findProductById(id).getPrice();
		assertEquals(true, price > 0, "Price is Zero/Negative for id ->" + id);
		// assertTrue(price>0);
	}	
	
	@Test
	@Timeout(1) // 1 second timeout
	void testWithTimeout() throws InterruptedException {
		// Simulate a long-running task
		Thread.sleep(2000); 
	}
	
	
//	@Test
//	void expectedException() throws InterruptedException {
//		 assertThrows(Exception.class, () -> {
//	            // Code that is expected to throw the exception
////	            throw new Exception("Excetion thrown");
//	        });
//	}
//	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
