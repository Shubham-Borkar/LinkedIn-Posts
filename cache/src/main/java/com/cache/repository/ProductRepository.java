package com.cache.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cache.pojo.Product;




@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {


}
