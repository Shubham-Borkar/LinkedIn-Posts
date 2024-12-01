package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.pojo.Product;
import com.app.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional(rollbackOn = Exception.class)
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepo;

	public ProductServiceImpl(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}

	@Override
	public List<Product> getAllProduct() {
		System.err.println("Service");
		return productRepo.findAll();
	}
	
	
	
	
	
	
	
	
	
	@Override
	public Product findProductById(int id) {

		return productRepo.findById(id).orElse(null);
	}
	
	public String privateMethod() {
		return "String returned from private methode";
	}

//	 //   commented for mocking,constructor injection needed
//		@Autowired
//		private ProductRepository productRepo;
//		public ProductServiceImpl(ProductRepository productRepo) {
//			this.productRepo=productRepo;
//	    }
		
	@Override
	public Product findUserById(int i) {
		Optional<Product> optionalProduct = productRepo.findById(i);
		return optionalProduct.orElse(null);	 
	}

}
