package com.mds.entity.h2;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "sbsb_product_mds")
public class Product {
	
	@Id
	@GeneratedValue(strategy = 
	 GenerationType.IDENTITY)
	@JsonProperty(access = Access.READ_ONLY)
	private int productId;
	private String name;
	private float price;
	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
	private List<Review> reviews;

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Product(String name, float price) {
		super();
		this.name = name;
		this.price = price;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

}
//@JsonProperty(access = Access.READ_ONLY)
//@JsonIgnore if eager fetch to avoid the lazy initialization error



