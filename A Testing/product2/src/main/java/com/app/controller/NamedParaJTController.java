package com.app.controller;

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

import com.app.pojo.Product;
import com.app.templaterepo.ProductRepositoryT;

@RestController
@RequestMapping("/named-para-jdbc-temp")
public class NamedParaJTController {

	@Autowired
	private ProductRepositoryT customProductRepo;

	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProductById(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.OK).body(customProductRepo.getProductByIdNQ(id));
	}

	@GetMapping("/named-para-temp/products")
	public ResponseEntity<?> getProducts() {
		return ResponseEntity.status(HttpStatus.OK).body(customProductRepo.getAllProductNQ());
	}

	@PostMapping("/post/product")
	public ResponseEntity<?> saveProduct(@RequestBody Product product) {
		return ResponseEntity.status(HttpStatus.OK).body(customProductRepo.saveProductNQ(product));
	}

	@PutMapping("/put/product/{id}")
	public ResponseEntity<?> editProductById(@RequestBody Product product, @PathVariable int id) {
		System.out.println(id+"&&&&&&&&&&&&&");
		return ResponseEntity.status(HttpStatus.OK).body(customProductRepo.updateProductNQ(product, id));
	}

	@DeleteMapping("/delete/product/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.OK).body(customProductRepo.deleteProductNQ(id));
	}

	@GetMapping("/em/product/{id}")
	public ResponseEntity<?> getProductByIdCR(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.OK).body(customProductRepo.getProductByIdCR(id));
	}
}
