package com.mds;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mds.entity.h2.Product;
import com.mds.entity.h2.Review;
import com.mds.repo.h2.ProductRepository;
import com.mds.repo.h2.ReviewRepository;

@SpringBootApplication
public class JavadApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(JavadApplication.class, args);
	}
	
	@Autowired
	private ProductRepository productRepo;	
	
	@Autowired
	private ReviewRepository reviewRepo;	
	
	@Override
	public void run(String... args) throws Exception {
		Product p1 = new Product("Watch", 4999);
		p1.setProductId(10);
		Product product = productRepo.save(p1);
		Review r1 = new Review(10, 8, "Good", product);
		Review r2 = new Review(11, 8, "Better", product);
		Review r3 = new Review(12, 8, "Best", product);		
		reviewRepo.save(r1);
		reviewRepo.save(r2);
		reviewRepo.save(r3);		
	}	

	
}





