package com.cache.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cache.pojo.Product;
import com.cache.pojo.Review;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

List<Review> findByProduct(Product p);
}
