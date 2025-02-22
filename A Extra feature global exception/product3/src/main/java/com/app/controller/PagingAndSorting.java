package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojo.Product;
import com.app.service.ProductService;

@RestController
@RequestMapping("/paging-and-sorting")
public class PagingAndSorting {

	@Autowired
	private ProductService productService;

	@GetMapping("/paging/products/{pageindex}/{elements}")
	public ResponseEntity<List<Product>> getAllReviewPaginated(@PathVariable int pageindex,
			@PathVariable int elements) {
		List<Product> products = productService.paginatedProduct(pageindex, elements);
		return ResponseEntity.status(HttpStatus.CREATED).body(products);
	}

	@GetMapping("/paging-sorting/products/{pageindex}/{elements}")
	public ResponseEntity<List<Product>> getAllReviewPaginatedAndSorted(@PathVariable int pageindex,
			@PathVariable int elements) {
		List<Product> products = productService.paginatedSortedProduct(pageindex, elements);
		return ResponseEntity.status(HttpStatus.CREATED).body(products);
	}

}
