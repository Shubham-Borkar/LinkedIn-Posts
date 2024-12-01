package com.mds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mds.entity.h2.Product;
import com.mds.entity.h2.Review;
import com.mds.entity.mysql.UserEntity;
import com.mds.repo.h2.ProductRepository;
import com.mds.repo.h2.ReviewRepository;
import com.mds.repo.mysql.UserRepository;

@RestController
public class TestController {

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ReviewRepository reviewRepo;
	
	@PostMapping("/add-product")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		Product productSaved = productRepo.save(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(productSaved);
	}

	@GetMapping("/get-all/product")
	public ResponseEntity<List<Product>> getAllProduct() {
		List<Product> allProduct = (List<Product>) productRepo.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(allProduct);
	}



	@PostMapping("/add-user")
	public ResponseEntity<UserEntity> addUser(@RequestBody UserEntity user) {
		UserEntity userSaved = userRepo.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
	}

	@GetMapping("/get-all/user")
	public ResponseEntity<List<UserEntity>> getAllUser() {
		List<UserEntity> allUser = (List<UserEntity>) userRepo.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(allUser);
	}
	
	@PostMapping("/add-review")
	public ResponseEntity<Review> addReview(@RequestBody Review review) {
		Review reviewSaved = reviewRepo.save(review);
		return ResponseEntity.status(HttpStatus.CREATED).body(reviewSaved);
	}
	@GetMapping("/get-all/review")
	public ResponseEntity<List<Review>> getAllReview() {
		List<Review> allreview = (List<Review>) reviewRepo.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(allreview);
	}

}
