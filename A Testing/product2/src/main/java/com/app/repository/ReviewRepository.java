package com.app.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojo.Product;
import com.app.pojo.Review;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

List<Review> findByProduct(Product p);
}
