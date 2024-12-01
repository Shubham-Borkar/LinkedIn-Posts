package com.app.pojo;

import java.util.Date;
import java.util.List;
import java.util.Locale.Category;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "sss_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
//@JsonFilter("remove_pid_filter")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = Access.READ_ONLY)
	private int productId;
	
	@Column
	private String name;
	@Column
	private float price;
	@JsonProperty(access = Access.READ_ONLY)
	@Column
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Review> reviews;
	
	

	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@JsonProperty(access = Access.READ_ONLY)
	private Date createdOn;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	@JsonProperty(access = Access.READ_ONLY)
	private Date modifiedOn;

}
