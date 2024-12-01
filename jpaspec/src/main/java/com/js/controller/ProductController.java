package com.js.controller;

import java.util.List;

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

import com.js.dto.ApiResponse;
import com.js.pojo.Product;
import com.js.repository.ProductRepository;
import com.js.repository.specification.ProductRepoOld;
import com.js.service.ProductService;
import com.js.service.ProductServiceImpl;

//@RestController
//@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private ProductRepoOld pRepo;
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id) {
		System.err.println("Called Get Methode");
		Product findProductById = productService.findProductById(id);
			return ResponseEntity.status(HttpStatus.OK).body(findProductById);
	}
	@GetMapping("/all")
	public ResponseEntity<List<Product>> getProductById() {
		System.err.println("Called Get All Methode");
		List<Product> allProduct = ((ProductServiceImpl)productService).findAllProduct();
			return ResponseEntity.status(HttpStatus.OK).body(allProduct);
	}
	@PostMapping
	public ResponseEntity<Product> getAllReview(@RequestBody  Product product) {
		System.err.println("Called Post Methode");
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
	
	@GetMapping("/good-rating/{id}")
	public ResponseEntity<List<Product>> ratingMorethanGiven(@PathVariable int id) {
		System.err.println("Called Get All Methode");
		List<Product> allProduct = productRepo.findByReviewsRatingsGreaterThan(id);
			return ResponseEntity.status(HttpStatus.OK).body(allProduct);
	}
	@GetMapping("/good--rating/{id}")
	public ResponseEntity<List<Product>> ratingMorethan(@PathVariable int id) {
		System.err.println("Called Get All Methode");
		List<Product> allProduct = productRepo.findByReviews_RatingsGreaterThan(id);
			return ResponseEntity.status(HttpStatus.OK).body(allProduct);
	}
	
	@GetMapping("/old-js/{id}/{price}/{name}")
	public ResponseEntity<List<Product>> simpleQuery(@PathVariable int id,@PathVariable String name,@PathVariable float price) {
		System.err.println("Called Get All Methode");
		List<Product> allProduct = pRepo.findBySimpleQuery(name, price, id);
			return ResponseEntity.status(HttpStatus.OK).body(allProduct);
	}
	




	
	
}
