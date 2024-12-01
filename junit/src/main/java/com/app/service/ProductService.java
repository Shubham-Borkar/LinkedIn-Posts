package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.ProductDto;
import com.app.pojo.Product;

/**
 * @author shubhamb
 *
 */
public interface ProductService {

	Product findProductById(int id);

	List<Product> getAllProduct();

	Product findUserById(int i);
	

}
