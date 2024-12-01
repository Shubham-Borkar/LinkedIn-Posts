package com.javad.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javad.pojo.Product;
import com.javad.pojo.Review;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

List<Review> findByProduct(Product p);
}
