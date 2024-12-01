package com.cache.service;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cache.dto.ApiResponse;
import com.cache.pojo.Product;
import com.cache.repository.ProductRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional(rollbackOn = Exception.class)
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	@Override
	@Cacheable(cacheNames = "product",key = "#id")
	public Product findProductById(int id) {
		//System.err.println("Using Database for SELECT");
		return productRepo.findById(id).orElse(null);
	}

	@Override
	@CachePut(cacheNames = "product",key="#id")
	public Product editProductById(int id, Product product) {
		System.err.println("Using Database for UPDATE");
		
		//if error occurs old data will be as it is, cache data will update only if no
		//exception/error occurs while executing this methode
		if(id==5) {
			System.err.println("Throwing Error");
			throw new RuntimeException();
		}
		
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

	
	//for beforeInvocation = true, cache will evict before method execute completely, 
	//if exception is their for id=4, if we try get product by id 4 so it calls db
	//default is false so if we delete 4 and there is exception, no need to call db 
	//again if 4 is already in key
	@Override
	//@CacheEvict(cacheNames = "product",key = "#id",beforeInvocation = true)
	public ApiResponse deleteById(int id) {
		if(id==4) {
			System.err.println("Throwing Error");
			throw new RuntimeException();
		}
		
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
		//System.err.println("Using Database for INSERT");
		return productRepo.save(product);	
	}



}
