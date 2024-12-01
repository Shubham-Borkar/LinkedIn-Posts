package com.js.service;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.js.dto.ApiResponse;
import com.js.pojo.Product;
import com.js.repository.ProductRepository;
import com.js.repository.specification.ProductSpecification;

import jakarta.transaction.Transactional;

@Service
@Transactional(rollbackOn = Exception.class)
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	@Override
	public Product findProductById(int id) {
		System.err.println("Using Database for SELECT");
		return productRepo.findById(id).orElse(null);
	}

	@Override
	public Product editProductById(int id, Product product) {
		System.err.println("Using Database for UPDATE");
		Optional<Product> optionalProduct = productRepo.findById(id);
		if (optionalProduct.isPresent()) {
			Product productRevived = optionalProduct.get();
			productRevived.setName(product.getName());
			productRevived.setPrice(product.getPrice());
			productRepo.save(productRevived);
			return productRevived;

		} else {
			return null;
		}

	}

	
	@Override
	public ApiResponse deleteById(int id) {
		System.err.println("Using Database for DELETE");
		if (productRepo.findById(id).isPresent()) {
			productRepo.deleteById(id);
			return new ApiResponse("Product with id " + id + " Deleted");
		} else {
			return new ApiResponse("Error while deleting product");
		}
	}

	
	@Override
	public Product addProduct(Product product) {
		return null;
	}
	public List<Product> findAllProduct() {
		
//		Specification<Product> specification = 
//		                  Specification.where(ProductSpecification.isNameContains("a")
//		                                 .and(ProductSpecification.isPriceEquals(499)));
//		
//		return (List<Product>) productRepo.findAll(ProductSpecification.joining("Great"));
		return (List<Product>) productRepo.findAll();

	}
	public List<Product> findAllProductJoiningReview() {	
		return productRepo.findAll(ProductSpecification.joining("Great"));
	}
	



}
