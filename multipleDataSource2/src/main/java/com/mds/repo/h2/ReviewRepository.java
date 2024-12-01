package com.mds.repo.h2;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mds.entity.h2.Product;
import com.mds.entity.h2.Review;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

List<Review> findByProduct(Product p);
}
