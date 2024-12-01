package com.app.product.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.service.ProductService;

@SpringBootTest
class ProductServiceTest {
	@Autowired
	private ProductService productService;

	// for normal test
	// @Test
	// for parameterized test
	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 3, 4, 5 })
	void testFindProductByIdD(int id) {
		System.err.println("#########");
		float price = productService.findProductById(id).getPrice();
		assertEquals(true, price >= 0, "Price is Negative for id ->" + id);
		// assertTrue(price>0);
	}
	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 3, 4, 5 })
	void testFindProductByIdE(int id) {
		System.err.println("#########");
		float price = productService.findProductById(id).getPrice();
		assertEquals(true, price >= 0, "Price is Negative for id ->" + id);
		// assertTrue(price>0);
	}

}
