package com.app.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//@RestController
@RequestMapping("/auth/products")
@CrossOrigin("*")
public class ProductController {

	@GetMapping("/view")
	public String viewProducts() {
		return "any one can view the products";
	}

	@GetMapping("/browse")
	public String browseCategories() {
		return "any authenticated user should be able to browse the categories";
	}

	@GetMapping("/purchase")
	public String purchaseProducts() {
		return "customer should be able to purchase products";
	}

	@PostMapping("/add")
	public String addProducts() {
		return "admin should be able to add the products";
	}

	@GetMapping("/delete")
	public String deleteProducts() {
		return "admin should be able to delete  the products";
	}
	@GetMapping("/one")
	public String apiForMobileone() {
		return "Product Api one";
	}
	@GetMapping("/useronly")
	public String apiForUserOnly() {
		return "Only user can access this api";
	}

}
