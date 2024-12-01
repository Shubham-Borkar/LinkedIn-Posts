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
	/**
	 * @return list of all products
	 */
	public List<Product> getAllProduct();

	/**
	 * @param id Id of product
	 * @return product for given id if found, else null
	 */
	public Product findProductById(int id);

	/**
	 * @param product to be added
	 * @return saved product if saved successful else null
	 */
	public Product addProduct(ProductDto product);

	/**
	 * @param id      id of Product to be edited
	 * @param product
	 * @return edited product if edited successful
	 */
	public Product editProductById(int id, Product product);

	/**
	 * @param id id on product to be deleted
	 */
	public ApiResponse deleteById(int id);

	/**
	 * @param pageindex page index start from 0
	 * @param pageno    page no
	 * @return
	 */
	public List<Product> paginatedProduct(int pageindex, int elements);

	/**
	 * @param pageindex page index start from 0
	 * @param pageno    page no
	 * @return
	 */
	public List<Product> paginatedSortedProduct(int pageindex, int elements);

	public void saveProduct(Product product)throws  Exception;
	
	public void saveProduct(List<Product> product)throws  Exception;
}
