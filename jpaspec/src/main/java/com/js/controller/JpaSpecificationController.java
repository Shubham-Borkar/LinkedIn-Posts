package com.js.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.js.pojo.Product;
import com.js.repository.ProductRepository;
import com.js.repository.specification.ProductRepoOld;
import com.js.repository.specification.ProductSpecification;

@RestController
public class JpaSpecificationController {
	
	@Autowired
	private ProductRepoOld pRepo;

	@GetMapping("/old-js/product-with-name-like/{name}")
	public ResponseEntity<List<Product>> nameLike(@PathVariable(name = "name") String name) {
		System.err.println("Products having name like");
		
		List<Product> allProduct = pRepo.findByName(name);
		
		allProduct
		.forEach(p-> System.out.println("Name -> "+p.getName()+", Id -> "+p.getProductId()));
		
		return ResponseEntity.status(HttpStatus.OK).body(allProduct);
	}
	
	
	
	
	
	
	
	
	
	
	@Autowired
	private ProductRepository productRepo;
	

	@GetMapping("/product-with-review-comment/{cmt}")
	public ResponseEntity<List<Product>> getProductWithReviewCommentAsGreat(@PathVariable(name = "cmt") String cmt) {
		System.err.println("Products which have Review comment as given cmt");
		
		List<Product> allProduct = productRepo.findAll(ProductSpecification.joining(cmt));
		
		return ResponseEntity.status(HttpStatus.OK).body(allProduct);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/product-with-name-equals/{name}")
	public ResponseEntity<List<Product>> getProductWithNameEquals(@PathVariable(name = "name") String name) {
		System.err.println("Product with name Equals");
		
		List<Product> allProduct = productRepo.findAll(ProductSpecification.isNameEquals(name));
		
		return ResponseEntity.status(HttpStatus.OK).body(allProduct);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/product-with-price-equals-anonymous-class/{price}")
	public ResponseEntity<List<Product>> getProductWithPriceEqualsN(@PathVariable(name = "price") float price) {
		System.err.println("Product with price Equals with Anonymous class");
		
		List<Product> allProduct = productRepo.findAll(ProductSpecification.isPriceEqualsNormal(price));
		
		return ResponseEntity.status(HttpStatus.OK).body(allProduct);
	}
	
	
	
	
	
	
	
	
	
	
	
	
		
	

	@GetMapping("/old-js/{id}/{price}/{name}")
	public ResponseEntity<List<Product>> simpleQuery(@PathVariable(name = "id") int id, 
													 @PathVariable(name = "name") String name,
													 @PathVariable(name = "price") float price) {
		System.err.println("Products with (given name or price greater than given input) and (id greater than given id)");
		
		List<Product> allProduct = pRepo.findBySimpleQuery(name, price, id);
		
		allProduct
		.forEach(p-> System.out.println("( "+p.getName()+" OR "+p.getPrice()+" ) AND ( "+p.getProductId()+" )"));
		
		return ResponseEntity.status(HttpStatus.OK).body(allProduct);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	
	
	
	
	
	@GetMapping("/product-with-price-equals-lambda/{price}")
	public ResponseEntity<List<Product>> getProductWithPriceEqualsL(@PathVariable(name = "price") float price) {
		System.err.println("Product with price Equals with Lambda expression");
		List<Product> allProduct = productRepo.findAll(ProductSpecification.isPriceEqualsLambda(price));
		return ResponseEntity.status(HttpStatus.OK).body(allProduct);
	}


	
	
	
	
	
	
	
	
	

//	@GetMapping("/{id}")
//	public ResponseEntity<Product> getProductById(@PathVariable(name = "id") int id) {
//		Product findProductById = productRepo.findById(id).orElse(null);
//		return ResponseEntity.status(HttpStatus.OK).body(findProductById);
//	}

}
