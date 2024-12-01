package com.js.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.js.pojo.Product;
import com.js.pojo.Review;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

	List<Review> findByProduct(Product p);
	
}
