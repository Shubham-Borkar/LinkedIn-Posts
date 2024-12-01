package com.js.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.js.pojo.Product;
import com.js.repository.ProductRepository;

@RestController
public class DevivedQueryController {

	@Autowired
	private ProductRepository productRepo;
	
	@GetMapping("/products/pageable/by-product-name")
	public ResponseEntity<List<Product>> getProductsByName(@RequestParam(name = "name") String name,
														   @RequestParam(defaultValue = "0") int page, 
														   @RequestParam(defaultValue = "10") int size) {
		System.err.println("Product with given name and list of product with given Page No. and Size using Page");		
		Pageable pageable = PageRequest.of(page, size);		
		Page<Product> productPage = productRepo.findByName(name, pageable);
	
		System.err.println("Total Elements -> "+productPage.getTotalElements()+
						   ", Total Pages -> "+productPage.getTotalPages());
		
		return ResponseEntity.status(HttpStatus.OK).body(productPage.getContent());
	}
	
	
	
	
	
	
	
	

	@GetMapping("/products/sorted/descending-with-column-as-sortedProperty-having-given-name")
	public ResponseEntity<List<Product>> getProductsByNameSorted(@RequestParam(name = "name") String name,
																 @RequestParam(name = "sortProperty") String sortProperty) {
		System.err.println("Product list with as given name, sorted as per given sortProperty and Order as per requirement ");
		
		Direction direction = Sort.Direction.DESC;		
		Sort sort = Sort.by(direction, sortProperty);
		return ResponseEntity.status(HttpStatus.OK).body(productRepo.findByName(name, sort));
	}
	
	
	

	@GetMapping("/products/slice/by-product-name")
	public ResponseEntity<List<Product>> getSlicedProductsByName(@RequestParam(name = "name") String name,
																 @RequestParam(defaultValue = "0") int page, 
																 @RequestParam(defaultValue = "10") int size) {
		System.err.println("Product with given name and list of product with given Page No. and Size using Slice");
		Pageable pageable = PageRequest.of(page, size);
		Slice<Product> productSlice = productRepo.findSlicedByName(name, pageable);
		
		return ResponseEntity.status(HttpStatus.OK).body(productSlice.getContent());
	}
	

	
	
	
	
	
	
	
	
	
	
	@GetMapping("/rating-greater-than/{rating}")
	public ResponseEntity<List<Product>> ratingMorethanGiven(@PathVariable(name = "rating") int rating) {
		System.err.println("Product having Review rating greater than given rating");
		List<Product> allProduct = productRepo.findByReviewsRatingsGreaterThan(rating);
		return ResponseEntity.status(HttpStatus.OK).body(allProduct);
	}
	
	@GetMapping("/rating-greater-than-using-underscore/{rating}")
	public ResponseEntity<List<Product>> ratingMorethan(@PathVariable(name = "rating") int rating) {
		System.err.println("Product having Review rating greater than given rating");
		List<Product> allProduct = productRepo.findByReviews_RatingsGreaterThan(rating);
		return ResponseEntity.status(HttpStatus.OK).body(allProduct);
	}




	

	

	@GetMapping("/products/limited/with-page-no-as-zero")
	public ResponseEntity<List<Product>> getLimitedProductsByName(@RequestParam(name = "name") String name,
																  @RequestParam(name = "sortProperty") String sortProperty, 
																  @RequestParam(name = "limit") int limit) {
		System.err.println("Product list with as given name, sorted as per given sortProperty, Order as per requirement and With given limit( return Page )");
		Direction direction = Sort.Direction.ASC;
		Sort sort = Sort.by(direction, sortProperty);
		Pageable pageable = PageRequest.of(0, limit, sort);
		return ResponseEntity.status(HttpStatus.OK).body(productRepo.findByName(name, pageable).getContent());

	}

//	@GetMapping("/products/limit/using-value-of-limit-variable")
//	public ResponseEntity<List<Product>> getLimitedProductsByNameAndLimit(@RequestParam(name = "name") String name,
//																		  @RequestParam(name = "sortProperty") String sortProperty, 	
//																		  @RequestParam(name = "limit") int limit) {
//		System.err.println("Product list with as given name, sorted as per given sortProperty, Order as per requirement and With given limit (returns List of Product)");
//		Direction direction = Sort.Direction.ASC;
//		Sort sort = Sort.by(direction, sortProperty);
//		return ResponseEntity.status(HttpStatus.OK).body(productRepo.findByName(name, sort, limit));
//
//	}


}
