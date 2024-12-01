package com.js.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.pojo.Review;
import com.js.repository.ReviewRepository;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepo;


	@Override
	public Review getReviewById(int id) {
		System.err.println("Called Review SELECT Methode");
		return reviewRepo.findById(id).orElse(null);
	}

}
