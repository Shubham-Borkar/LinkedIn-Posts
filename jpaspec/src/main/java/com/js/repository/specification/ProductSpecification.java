package com.js.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import com.js.pojo.Product;
import com.js.pojo.Review;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ProductSpecification {
	
	public static Specification<Product> joining(String key) {
		
		return (root, query, cb) -> {
			Join<Review, Product> productReview=root.join("reviews");
			return cb.equal(productReview.get("comment"),key);
			};	
			
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static Specification<Product> isPriceEqualsNormal(float price) {
		
		
		
		

		Specification<Product> specification = new Specification<Product>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Product> root, 
										 CriteriaQuery<?> query,
										 CriteriaBuilder cb) {
				return cb.equal(root.get("price"), price);
			}
		};
		
		// Lambda Expression : This is more concise and easier to read
		
		// Specification<Product> specification=			
		//		(root, query, c) -> {return c.equal(root.get("price"), price);};
		
		
		
		
		
		
		return specification;
	}
	
	
	public static Specification<Product> isPriceEqualsLambda(float price) { 
		
		
		Specification<Product> specification=			
			(root, query, cb) -> {return cb.equal(root.get("price"), price);};	
			
			return specification;
			
			
	}
	
	
	
	
	
	public static Specification<Product> isNameEquals(String name) {
		return (root, query, cb) -> {
			return cb.equal(root.get("name"), name);
			};		
	}
	
	
	
	
	
	
	
	
	

	
	
	
	

}















//public static Specification<Product> isNameContains(String key) {
//return (root, query, cb) -> {
//	return cb.like(root.get("price"), "%"+key+"%");
//	};		
//}
