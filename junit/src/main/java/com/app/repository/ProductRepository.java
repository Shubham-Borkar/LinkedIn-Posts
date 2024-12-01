package com.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.pojo.Product;
import java.util.*;



@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

	Product findProductByName(String name);
	
	  @Query(value = "SELECT product_id,name,price FROM sbsb_product", nativeQuery = true)
	  List<Product> nativeQuery();
	  
	  @Query(value = "SELECT new com.app.pojo.Product(name,price)FROM Product")
	  List<Product> nativeQueryyy();
	  
}
