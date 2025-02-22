package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.pojo.Review;

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

	/**
	 * @param review    review to be added
	 * @param productId id of product for which review will be added
	 * @return if saved successfully return review else null
	 */
	public Review saveReviewByProductId(Review review, int productId);

	/**
	 * @param productId productId id of product for which reviews list to be found
	 * @return list of review if found else null
	 */
	public List<Review> getReviewsByProductId(int productId);

	/**
	 * @param id     of Review to be edited
	 * @param review data
	 * @return
	 */
	public Review editReviewById(int id, Review review);

	/**
	 * @param id of review to be deleted
	 * @return
	 */
	public ApiResponse deleteById(int id);

}
