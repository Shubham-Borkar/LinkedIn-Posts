package com.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cache.dto.ApiResponse;
import com.cache.pojo.Product;
import com.cache.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id) {
		//System.err.println("Called Get Methode");
		Product findProductById = productService.findProductById(id);
			return ResponseEntity.status(HttpStatus.OK).body(findProductById);
	}
	@PostMapping
	public ResponseEntity<Product> getAllReview(@RequestBody  Product product) {
		//System.err.println("Called Post Methode");
		Product productSaved = productService.addProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(productSaved);
	}

	@PutMapping("/edit/{product-id}")
	public ResponseEntity<Product> editProductById(@PathVariable(value = "product-id") int id, @RequestBody Product product) {
		System.err.println("Called Put Methode");
		Product findProductById = productService.editProductById(id, product);
			return ResponseEntity.status(HttpStatus.OK).body(findProductById);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteProductById(@PathVariable int id) {
		System.err.println("Called Delete Methode");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(productService.deleteById(id));
	}
	
}
