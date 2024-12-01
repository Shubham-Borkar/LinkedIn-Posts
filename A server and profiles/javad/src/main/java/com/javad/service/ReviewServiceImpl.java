package com.javad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javad.pojo.Review;
import com.javad.repository.ReviewRepository;

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
