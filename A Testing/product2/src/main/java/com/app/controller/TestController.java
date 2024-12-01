package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.ProductDto;
import com.app.globalexceptionhandler.StatusCodeException;
import com.app.pojo.Product;
import com.app.pojo.Review;
import com.app.service.ProductService;

import jakarta.validation.Valid;


@RestController
public class TestController {
	

	@PostMapping
	public void getProduct(@RequestBody @Valid ProductDto pd) {
		System.err.println(pd.toString());
	}

	@GetMapping("/error-info/{id}")
	public ResponseEntity<?> getErrorInfo(@PathVariable(value = "id") int id) throws Exception  {

		if (id == 0) {
			throw new StatusCodeException("StatusCodeException for Input 0");
		} else if (id == 1) {
			throw new RuntimeException("RuntimeException for Input 1");
		} else if (id == 2) {
			throw new Exception("Exception for Input 2");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body("working fine");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@Autowired
	private ProductService productService;
	
	
	
	@GetMapping("/statuscodeexception/{id}")
	public ResponseEntity<?> getAllProducts(@PathVariable(value = "id") int id) 
			throws StatusCodeException {
		
		if(id==0) {
				throw new StatusCodeException("Input is 0");
		}else {
			return ResponseEntity.status(HttpStatus.OK).body("working fine");
			
		}	
		
	}

	
	
	
	
	
	@GetMapping("/products")
	public ResponseEntity<?> getAllProducts() {
		System.err.println("Controller");

		List<Product> allProduct = productService.getAllProduct();
		if (allProduct.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error in getting Products"));
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(allProduct);
		}
	}
	
	@GetMapping("/agent")
	public ResponseEntity<?> getAgent(@PathVariable int id,@RequestHeader(value = "User-Agent") String userAgent) throws StatusCodeException  {
		System.out.println("User Agent ->"+userAgent);
		if(id==0) {
				throw new StatusCodeException("Input is 0");
		}else {
			return ResponseEntity.status(HttpStatus.OK).body("working fine");
			
		}		
	}


	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id) {
		System.err.println("Controller");
		Product findProductById = productService.findProductById(id);

		if (findProductById == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(findProductById);
		}
	}

	@PostMapping("/product")
	public ResponseEntity<Product> getAllReview(@RequestBody  ProductDto product) {
		Product productSaved = productService.addProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(productSaved);
	}

	@PutMapping("/edit-product/{product-id}")
	public ResponseEntity<?> editProductById(@PathVariable(value = "product-id") int id, @RequestBody Product product) {
		Product findProductById = productService.editProductById(id, product);
		if (findProductById == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse("Error While Editing Product"));
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(findProductById);
		}
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<ApiResponse> deleteProductById(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(productService.deleteById(id));
	}
	
	@GetMapping("/productsaving")
	public ResponseEntity<Review> savingentity() throws Exception {
		Product product=new Product(1, "tcheck1", 0, null,null,null);
		productService.saveProduct(product);
	
	
	
			return ResponseEntity.status(HttpStatus.OK).body(null);
		
	}

	@PostMapping("/product/exc")
	public ResponseEntity<Product> getAllReview(@RequestBody  Product product) throws Exception {
		productService.saveProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
	@PostMapping("/product/excc")
	public ResponseEntity<Product> getAllRevieww(@RequestBody  List<Product> product) throws Exception {
		productService.saveProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
}
