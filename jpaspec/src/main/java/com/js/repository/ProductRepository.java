package com.js.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.js.pojo.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
	

	// one
	List<Product> findByReviewsRatingsGreaterThan(int rating);

	// Both this one and two are similar just using _ for better separation between two Entity
	// two
	List<Product> findByReviews_RatingsGreaterThan(int rating);

	Page<Product> findByName(String name, Pageable pageable);

	Slice<Product> findSlicedByName(String name, Pageable pageable);

	List<Product> findByName(String name, Sort sort);

}













//List<Product> findByName(String name, Sort sort, int limit);