package com.spring.batch.entity;

import java.time.LocalDateTime;

public class Product {

	private int id;

	private float price;

	private String name;

	private boolean processed;

	private LocalDateTime datetime;

	public Product(int id, float price, String name, boolean processed, LocalDateTime datetime) {
		this.id = id;
		this.price = price;
		this.name = name;
		this.processed = processed;
		this.datetime = datetime;
	}

	public Product() {

	}

	public Product(int id, float price, String name) {
		super();
		this.id = id;
		this.price = price;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	public LocalDateTime getDatetime() {
		return datetime;
	}

	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", price=" + price + ", name=" + name + ", processed=" + processed + ", datetime="
				+ datetime + "]";
	}

}
