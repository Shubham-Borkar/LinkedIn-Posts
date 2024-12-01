package com.js.service;

import java.util.List;

import com.js.dto.ApiResponse;
import com.js.pojo.Product;

/**
 * @author shubhamb
 *
 */
public interface ProductService {


	public Product findProductById(int id);

	public Product addProduct(Product product);

	public Product editProductById(int id, Product product);

	public ApiResponse deleteById(int id); 
	
	public List<Product> findAllProductJoiningReview();

}
