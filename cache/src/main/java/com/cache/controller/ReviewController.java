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
import com.cache.pojo.Review;
import com.cache.service.ProductService;
import com.cache.service.ReviewService;

@RestController
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Review> getProductById(@PathVariable int id) {
		System.err.println("Called Review Get Methode");
		Review reviewById = reviewService.getReviewById(id);
			return ResponseEntity.status(HttpStatus.OK).body(reviewById);
	}
	
}
