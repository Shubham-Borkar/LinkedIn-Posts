package com.app.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.pojo.Product;



@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

	Product findProductByName(String name);
	
	  @Query(value = "SELECT product_id,name,price FROM sbsb_product", nativeQuery = true)
	  List<Product> nativeQuery();
	  
	  @Query(value = "SELECT new com.app.pojo.Product(name,price)FROM Product")
	  List<Product> nativeQueryyy();
	  
//		@Query(name = "select * from sbsb_product",nativeQuery = true)
//		public List<Product> getProddd();	

}
