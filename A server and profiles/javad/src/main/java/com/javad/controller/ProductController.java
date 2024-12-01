package com.javad.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javad.dto.ApiResponse;
import com.javad.dto.ImageList;
import com.javad.pojo.Product;
import com.javad.repository.specification.ProductRepo;
import com.javad.service.ProductService;
import com.javad.service.ProductServiceImpl;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@PostMapping(value = "/upload-only-image/{userId}",consumes ="multipart/form-data" )
	public ResponseEntity<?> postJustProductImage(
	            @RequestParam(value = "image") MultipartFile image,
	            @PathVariable(value = "userId") int userId)
	            {
	   return ResponseEntity.ok("Image received successfully, Path Variable Allowed");
	}

	
	
	@PostMapping(value = "/upload-image-with-requestbody",consumes ="multipart/form-data" )
	public ResponseEntity<?> postProductErrorCausingWithRequstBody(
	            @RequestParam(value = "image") MultipartFile image,
	            @RequestBody Product product)
	            {
	   return ResponseEntity.ok("Image received successfully, with RequestBody");
	}

 

	@PostMapping(value = "/upload-image-with-product-json-string", 
				 consumes = { MediaType.APPLICATION_JSON_VALUE,
						      MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> postProductImageJS(@RequestPart("product") String product,
											    @RequestPart("image") MultipartFile imageData) throws IOException {
		
		Product productFromJsonSring = new ObjectMapper().readValue(product, Product.class);
		System.err.println(productFromJsonSring);
		
		return ResponseEntity.ok("Image and Entity received successfully, Entity -> "+productFromJsonSring);
	}
	
	
	

    @PostMapping(value="/upload-image-with-product-json-file",
    		     consumes = "multipart/form-data")
    public ResponseEntity<String> postProductImageJF(@RequestPart("product") MultipartFile productFile,
    												 @RequestPart("image") MultipartFile imageFile) throws StreamReadException, DatabindException, IOException {

		Product productFromjsonFile = new ObjectMapper().readValue(productFile.getBytes(), Product.class);
		System.err.println(productFromjsonFile);

		return ResponseEntity.ok("Image and Entity received successfully, Entity -> "+productFromjsonFile);
    }
	
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//	@PostMapping(value = "/requestpart/json", 
//			consumes = {MediaType.APPLICATION_JSON_VALUE,
//						MediaType.MULTIPART_FORM_DATA_VALUE})
//public ResponseEntity<?> postProductImage1(@RequestPart("list") String list,
//                                            @RequestPart("file") MultipartFile imageData) throws IOException {
//	 ObjectMapper mapper = new ObjectMapper();
//	 ImageList integerList = mapper.readValue(list, ImageList.class);
//        System.out.println("List of integers: " + integerList.getList());
//    return ResponseEntity.ok().build();
//}
//
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//
//	@GetMapping("/{id}")
//	public ResponseEntity<Product> getProductById(@PathVariable int id) {
//		System.err.println("Called Get Methode");
//		Product findProductById = productService.findProductById(id);
//			return ResponseEntity.status(HttpStatus.OK).body(findProductById);
//	}
//	@GetMapping("/all")
//	public ResponseEntity<List<Product>> getProductById() {
//		System.err.println("Called Get All Methode");
//		List<Product> allProduct = ((ProductServiceImpl)productService).findAllProduct();
//			return ResponseEntity.status(HttpStatus.OK).body(allProduct);
//	}
//	@PostMapping
//	public ResponseEntity<Product> getAllReview(@RequestBody  Product product) {
//		System.err.println("Called Post Methode");
//		Product productSaved = productService.addProduct(product);
//		return ResponseEntity.status(HttpStatus.CREATED).body(productSaved);
//	}
//
//	@PutMapping("/edit/{product-id}")
//	public ResponseEntity<Product> editProductById(@PathVariable(value = "product-id") int id, @RequestBody Product product) {
//		System.err.println("Called Put Methode");
//		Product findProductById = productService.editProductById(id, product);
//			return ResponseEntity.status(HttpStatus.OK).body(findProductById);
//	}
//
//	@DeleteMapping("/{id}")
//	public ResponseEntity<ApiResponse> deleteProductById(@PathVariable int id) {
//		System.err.println("Called Delete Methode");
//		return ResponseEntity.status(HttpStatus.ACCEPTED).body(productService.deleteById(id));
//	}
//	
//	@Autowired
//	private ProductRepo pRepo;
//	@GetMapping("/old-js/{id}/{price}/{name}")
//	public ResponseEntity<List<Product>> simpleQuery(@PathVariable int id,@PathVariable String name,@PathVariable float price) {
//		System.err.println("Called Get All Methode");
//		List<Product> allProduct = pRepo.findBySimpleQuery(name, price, id);
//			return ResponseEntity.status(HttpStatus.OK).body(allProduct);
//	}
//	
//	
//    
//    
//	@Autowired
//	private ProductService productService;
//	
//	

}
