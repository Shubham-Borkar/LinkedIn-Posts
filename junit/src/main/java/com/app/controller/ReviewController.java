package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.pojo.Product;
import com.app.pojo.Review;
import com.app.repository.ProductRepository;
import com.app.repository.ReviewRepository;
import com.app.service.ReviewService;




@RestController
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/reviews/{productId}")
	public ResponseEntity<List<Review>> getReviewsForProduct(@PathVariable int productId) {
		List<Review> reviewList = reviewService.getReviewsByProductId(productId);
		if (reviewList == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(reviewList);
		}
	}
	
	@PutMapping("/edit-product/{productId}")
	public ResponseEntity<?> editProductById(@PathVariable(value = "productId") int id, @RequestBody Product review) {

		//sample edit api
			return ResponseEntity.status(HttpStatus.OK).body("ok");

	}
	
	
	
	
	
	
	
	
	
	
	
	@PutMapping("/edit-productt/{productId}")
	public ResponseEntity<?> editReviewById(@PathVariable(value = "productId") int id, @RequestBody Review review) {
		Review findReviewById = reviewService.editReviewById(id, review);
		if (findReviewById == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse("Error While Editing Review"));
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(findReviewById);
		}
	}

	
	
	
	
	
	
	
	
	
	
	

	@GetMapping("/review/{reviewId}")
	public ResponseEntity<Review> getReviewById(@PathVariable int reviewId) {
		Review reviewById = reviewService.getReviewById(reviewId);
		if (reviewById == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(reviewById);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Autowired
	private ReviewRepository rRepo;
	
	@Autowired
	private ProductRepository pRepo;
	
	
	
	
	@PostMapping("/review/{productId}")
	public ResponseEntity<Review> addReviewByProductId(@RequestBody Review review, @PathVariable int productId) {
		Review reviewById = reviewService.saveReviewByProductId(review, productId);
		if (reviewById == null) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(reviewById);
		}
	}

	


	
	@GetMapping("/reviewcustom/{pid}")
	public ResponseEntity<Review> getReviewsByPid(@PathVariable int pid) {
		
		Product p=pRepo.findById(pid).get();
		List<Review> rlist=rRepo.findByProduct(p);
		

		System.out.println(rlist==null);
			return ResponseEntity.status(HttpStatus.OK).body(rlist.get(0));
		
	}
	


}
