package com.app.controller.extrafeatures;

import java.util.List;
import java.util.Locale;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//for hateoas
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.app.dto.ApiResponse;
import com.app.dto.ProductDto;
import com.app.dto.ValidationChecker;
import com.app.pojo.Category;
import com.app.pojo.Product;
import com.app.service.ProductService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/extra-features")
public class ExtraFeature {
	@Autowired
	private ProductService productService;

	@Autowired
	private MessageSource messageSource;

	@PostMapping("/check-validation")
	public ResponseEntity<ApiResponse> checkValidation(@RequestBody @Valid ValidationChecker validate) {
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Validation Successful"));
	}

	// Add Header, Accept:application/xml to get xml response
	@GetMapping("/products-content-negotiation")
	public ResponseEntity<?> getAllProducts() {
		List<Product> allProduct = productService.getAllProduct();
		if (allProduct.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error in getting Products"));
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(allProduct);
		}
	}

	// Add Header Accept-Language:marathi
	@GetMapping("/get-message-i18n")
	public String getMessage() {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
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

	// to get data and actions which can be performed on it (links) 
	@GetMapping("/getting-links-with-response")
	public EntityModel<Product> getAvailableLinks() {
		// method on needs static import
		Product product = null;
		EntityModel<Product> entityModelofProduct = EntityModel.of(product);
		WebMvcLinkBuilder getAllProduct = linkTo(methodOn(this.getClass()).getAllProducts());
		entityModelofProduct.add(getAllProduct.withRel("allProduct"));
		return entityModelofProduct;
	}

	
	
	
	@GetMapping("/normal-get-Product")
	public Product getProduct() {
		Category category = new Category(22, "Smart Phone", null);
		Product product = Product.builder()
								 .productId( 101 )
								 .name("Samsung S24 Ultra")
								 .price(89999.0f)
								 .category(category)
								 .build();

	
		return product;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/get-Product-using-model-mapper")
	public ProductDto getProductModelMapper() {
		Category category = new Category(22, "Smart Phone", null);
		Product product = Product.builder()
								 .productId( 101 )
								 .name("Samsung S24 Ultra")
								 .price(89999.0f)
								 .category(category)
								 .build();

		ProductDto dto = modelMapper.map(product, ProductDto.class);
	
		return dto;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/dynamic-filtered-product")
	public MappingJacksonValue productEntityWithOutProductId() {
		Category category = new Category(22, "Smart Phone", null);

		Product product = Product.builder()
								 .productId( 101 )
								 .name("Samsung S24 Ultra")
								 .price(89999.0f)
								 .category(category)
								 .build();

		SimpleBeanPropertyFilter removeIdFilter = 
				SimpleBeanPropertyFilter.filterOutAllExcept("name", "price");

		FilterProvider filter = 
				new SimpleFilterProvider().addFilter("remove_pid_filter", removeIdFilter);

		MappingJacksonValue mjv = new MappingJacksonValue(product);
		mjv.setFilters(filter);
		return mjv;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
