package com.app.controller.extrafeatures;

//for hateoas
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.controller.ReviewController;
import com.app.dto.ApiResponse;
import com.app.dto.ValidationChecker;
import com.app.pojo.Product;
import com.app.service.ProductService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/extra-features")
public class ExtraFeature {
	
	// to get data and actions which can be performed on it (links)
	@GetMapping("/hateoas-links-with-response")
	public EntityModel<Product> getAvailableLinks() {

		// sample Data to be returned
		Product product = new Product(888, "888", 88.8f, null,null,null);

		EntityModel<Product> entityModelofProduct = EntityModel.of(product);
		
		//Not Recommended
		entityModelofProduct.add(Link.of("https://hardcoded/link/for/performing/any/actions/on/product"));
		
		WebMvcLinkBuilder getReviewForThisProduct = 
				linkTo(methodOn(ReviewController.class).getReviewsForProduct(product.getProductId()));
		entityModelofProduct.add(getReviewForThisProduct.withRel("get-all-product-reviews-at"));
		
	
		Link editProductLink = linkTo(methodOn(ReviewController.class).editProductById(product.getProductId(),new Product()))
																	  .withRel("edit-product-at");
		entityModelofProduct.add(editProductLink);
		
		return entityModelofProduct;

	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/get-all-products")
	public ResponseEntity<?> getAllProducts() {
		List<Product> allProduct = productService.getAllProduct();
		if (allProduct.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error in getting Products"));
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(allProduct);
		}
	}
		
	
	
	
	
	
		
		
		// Add Header, Accept:application/xml to get xml response
		@GetMapping("/products-content-negotiation")
		public ResponseEntity<?> getAllProductss() {
			List<Product> allProduct = productService.getAllProduct();
			if (allProduct.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error in getting Products"));
			} else {
				return ResponseEntity.status(HttpStatus.OK).body(allProduct);
			}
		}

		
		
		
		
		
		
		
		
		
		
		
		
		
	
	@GetMapping("/dynamic-filtered-product")
	public MappingJacksonValue productEntityWithOutProductId() {
		Product product = new Product(888, "888", 88.8f, null,null,null);

		SimpleBeanPropertyFilter removeIdFilter = SimpleBeanPropertyFilter.filterOutAllExcept("name", "price");
		

		FilterProvider filter = new SimpleFilterProvider().addFilter("remove_pid_filter", removeIdFilter);

		MappingJacksonValue mjv = new MappingJacksonValue(product);
		mjv.setFilters(filter);
		return mjv;
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Add Header V:1 or V:2 to get the specified version
		@GetMapping(value = "/version", headers = "V=1")
		public String getVersion1() {
			return "Getting Data from Version 1";
		}

		@GetMapping(value = "/version", headers = "V=2") // X-API-VERSION
		public String getVersion2() {
			return "Getting Data from Version 2";
		}

	
	
	
	
	
	
	
	
	
	
	
	
	


	
	
	
	
	
	
	
	
	
	
	// Add Header Accept-Language : marathi
	@GetMapping("/get-message-i18n")
	public String getMessage() {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Autowired
	private ProductService productService;

	@Autowired
	private MessageSource messageSource;

	@PostMapping("/check-validation")
	public ResponseEntity<ApiResponse> checkValidation(@RequestBody @Valid ValidationChecker validate) {
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Validation Successful"));
	}



	
	
	
}
