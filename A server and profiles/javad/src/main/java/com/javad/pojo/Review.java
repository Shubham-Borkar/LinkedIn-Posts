package com.javad.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="sbsb_review")
public class Review implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = Access.READ_ONLY)
	private int review_Id;
	@Column
	private int ratings;
	@Column
	private String comment;
	@ManyToOne
	@JoinColumn(name="product_id_fk")
	@JsonIgnore
	private Product product;

	public Review() {
		super();
	}
	
	public Review(int review_Id, int ratings, String commment, Product product) {
		super();
		this.review_Id = review_Id;
		this.ratings = ratings;
		this.comment = commment;
		this.product = product;
	}

	public int getReview_Id() {
		return review_Id;
	}
	public void setReview_Id(int review_Id) {
		this.review_Id = review_Id;
	}
	public int getRatings() {
		return ratings;
	}
	public void setRatings(int ratings) {
		this.ratings = ratings;
	}
	public String getCommment() {
		return comment;
	}
	public void setCommment(String commment) {
		this.comment = commment;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "Review [review_Id=" + review_Id + ", ratings=" + ratings + ", commment=" + comment + "]";
	}
	
}
