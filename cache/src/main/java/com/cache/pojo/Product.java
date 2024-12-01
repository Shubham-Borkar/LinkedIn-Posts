package com.cache.pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "sbsb_product")
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = Access.READ_ONLY)
	private int productId;
	@Column
	private String name;
	@Column
	private float price;
	
	
	
	

	@Column(name = "parameters")
	@Convert(converter = MapJsonConverter.class)
	private Map<String, String> parameters;
	
	//  insert into sbsb_product (product_id,price,name,parameters)
	//  values(6,'2999.00','Watch','{"name": "Shubham", "RollNo": "45"}');
	
	
	
	
	
	
	@JsonProperty(access = Access.READ_ONLY)
	@Column
	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
	private List<Review> reviews;
	public Product(String name, float price) {
		super();
		this.name = name;
		this.price = price;
	}


}
