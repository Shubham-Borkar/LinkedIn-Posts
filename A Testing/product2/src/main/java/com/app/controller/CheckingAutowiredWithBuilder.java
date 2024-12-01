package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojo.AutowiringBeanOne;
import com.app.pojo.AutowiringBeanTwo;

@RestController
@RequestMapping("/testingautowired")
public class CheckingAutowiredWithBuilder {

	@Autowired
	private AutowiringBeanOne one;
	@Autowired
	private AutowiringBeanTwo two;
	
	
	@GetMapping("/one")
	public String testone() {
		System.out.println("Before First Injection ->" +one.getCin().toString());
		one.getCin().buildOne("setting one using builder pattern");
		System.out.println("After First Injection ->" +one.getCin().toString());
		return one.getCin().toString();
	}
	@GetMapping("/two")
	public String testtwo() {
		System.out.println("Before Second Injection ->" +two.getCin().toString());
		two.getCin().buildTwo("setting two using builder pattern");
		System.out.println("After Second Injection ->" +two.getCin().toString());
		return two.getCin().toString();
	}
	@GetMapping("/final")
	public void finaltest() {

		System.out.println("bean one ->" +one.getCin().toString());
		System.out.println("bean two ->" +two.getCin().toString());
		
	}
	
	
}
