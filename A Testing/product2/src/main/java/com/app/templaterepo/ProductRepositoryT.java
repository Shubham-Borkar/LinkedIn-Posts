package com.app.templaterepo;


import java.util.List;

import com.app.pojo.Product;


public interface ProductRepositoryT {
	Product getProductByIdCR(int productId);

	Boolean saveProductNQ(Product product);
	
	Product getProductByIdNQ(int productId);
	
	List<Product> getAllProductNQ();

	int updateProductNQ(Product product,int id);

	int deleteProductNQ(int id);

}
