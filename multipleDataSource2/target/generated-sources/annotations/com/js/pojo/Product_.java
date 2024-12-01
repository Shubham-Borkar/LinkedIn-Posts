package com.js.pojo;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Product.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Product_ {

	
	/**
	 * @see com.js.pojo.Product#productId
	 **/
	public static volatile SingularAttribute<Product, Integer> productId;
	
	/**
	 * @see com.js.pojo.Product#reviews
	 **/
	public static volatile ListAttribute<Product, Review> reviews;
	
	/**
	 * @see com.js.pojo.Product#price
	 **/
	public static volatile SingularAttribute<Product, Float> price;
	
	/**
	 * @see com.js.pojo.Product#name
	 **/
	public static volatile SingularAttribute<Product, String> name;
	
	/**
	 * @see com.js.pojo.Product
	 **/
	public static volatile EntityType<Product> class_;

	public static final String PRODUCT_ID = "productId";
	public static final String REVIEWS = "reviews";
	public static final String PRICE = "price";
	public static final String NAME = "name";

}

