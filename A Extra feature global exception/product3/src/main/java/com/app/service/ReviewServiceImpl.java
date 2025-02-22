package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.ApiResponse;
import com.app.pojo.Product;
import com.app.pojo.Review;
import com.app.repository.ProductRepository;
import com.app.repository.ReviewRepository;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepo;

	@Autowired
	private ProductRepository productRepo;

	@Override
	public Review getReviewById(int id) {
		return reviewRepo.findById(id).orElse(null);
	}

	@Override
	public Review saveReviewByProductId(Review review, int productId) {
		Optional<Product> product = productRepo.findById(productId);
		if (product.isPresent()) {
			review.setProduct(product.get());
			productRepo.save(product.get());
			Review savedReview = reviewRepo.save(review);
			return savedReview;
		} else {
			return null;
		}

	}

	@Override
	public List<Review> getReviewsByProductId(int productId) {
		Optional<Product> product = productRepo.findById(productId);
		if (product.isPresent()) {
			return product.get().getReviews();
		} else {
			return null;
		}

	}

	@Override
	public Review editReviewById(int id, Review review) {
		Optional<Review> optionalReview = reviewRepo.findById(id);
		if (optionalReview.isPresent()) {
			Review reviewReceived = optionalReview.get();
			reviewReceived.setRatings(review.getRatings());
			reviewReceived.setCommment(review.getCommment());
			reviewRepo.save(reviewReceived);
			return reviewReceived;

		} else {
			return null;
		}
	}

	@Override
	public ApiResponse deleteById(int id) {
		if (reviewRepo.findById(id).isPresent()) {
			reviewRepo.deleteById(id);
			return new ApiResponse("Review with id " + id + " Deleted");
		} else {
			return new ApiResponse("Error while deleting review");
		}
	}

}
