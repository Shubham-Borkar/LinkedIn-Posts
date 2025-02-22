package com.app.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.pojo.Review;
import com.app.service.ReviewService;

@RestController
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@GetMapping("/review/{reviewId}")
	public ResponseEntity<Review> getReviewById(@PathVariable int reviewId) {
		Review reviewById = reviewService.getReviewById(reviewId);
		if (reviewById == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(reviewById);
		}
	}

	@GetMapping("/reviews/{productId}")
	public ResponseEntity<List<Review>> getReviewsForProduct(@PathVariable int productId) {
		List<Review> reviewList = reviewService.getReviewsByProductId(productId);
		if (reviewList == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(reviewList);
		}
	}

	@PostMapping("/review/{productId}")
	public ResponseEntity<Review> addReviewByProductId(@RequestBody Review review, @PathVariable int productId) {
		Review reviewById = reviewService.saveReviewByProductId(review, productId);
		if (reviewById == null) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(reviewById);
		}
	}

	@PutMapping("/edit-review/{review-id}")
	public ResponseEntity<?> editProductById(@PathVariable(value = "review-id") int id, @RequestBody Review review) {
		Review findReviewById = reviewService.editReviewById(id, review);
		if (findReviewById == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse("Error While Editing Review"));
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(findReviewById);
		}
	}

	@DeleteMapping("/review/{id}")
	public ResponseEntity<ApiResponse> deleteProductById(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(reviewService.deleteById(id));
	}

}
