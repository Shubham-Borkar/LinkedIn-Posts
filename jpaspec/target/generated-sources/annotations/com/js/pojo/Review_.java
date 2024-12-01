package com.js.pojo;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Review.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Review_ {

	
	/**
	 * @see com.js.pojo.Review#review_Id
	 **/
	public static volatile SingularAttribute<Review, Integer> review_Id;
	
	/**
	 * @see com.js.pojo.Review#product
	 **/
	public static volatile SingularAttribute<Review, Product> product;
	
	/**
	 * @see com.js.pojo.Review#ratings
	 **/
	public static volatile SingularAttribute<Review, Integer> ratings;
	
	/**
	 * @see com.js.pojo.Review#comment
	 **/
	public static volatile SingularAttribute<Review, String> comment;
	
	/**
	 * @see com.js.pojo.Review
	 **/
	public static volatile EntityType<Review> class_;

	public static final String REVIEW__ID = "review_Id";
	public static final String PRODUCT = "product";
	public static final String RATINGS = "ratings";
	public static final String COMMENT = "comment";

}

