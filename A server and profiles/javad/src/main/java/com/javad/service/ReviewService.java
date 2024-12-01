package com.javad.service;

import com.javad.pojo.Review;

/**
 * @author shubhamb
 *
 */
public interface ReviewService {

	/**
	 * @param id of review to be found
	 * @return if found return review, if not then return null
	 */
	public Review getReviewById(int id);

}
