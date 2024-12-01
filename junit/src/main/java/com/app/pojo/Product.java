package com.app.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "sbsb_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@JsonFilter("remove_pid_filter")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@JsonProperty(access = Access.READ_ONLY)
	private int productId;
	@Column
	private String name;
	@Column
	private float price;
	//@JsonProperty(access = Access.READ_ONLY)
	@Column
	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
	private List<Review> reviews;
	public Product(String name, float price) {
		super();
		this.name = name;
		this.price = price;
	}
	

}
