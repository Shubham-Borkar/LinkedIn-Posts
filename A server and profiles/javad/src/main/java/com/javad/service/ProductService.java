package com.javad.service;

import com.javad.dto.ApiResponse;
import com.javad.pojo.Product;

/**
 * @author shubhamb
 *
 */
public interface ProductService {


	public Product findProductById(int id);

	public Product addProduct(Product product);

	public Product editProductById(int id, Product product);

	public ApiResponse deleteById(int id); 

}
