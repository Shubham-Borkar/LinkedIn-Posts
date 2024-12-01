package com.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cache.service.GeneralService;

@RestController
@RequestMapping("/redis")
public class RedisController {
	@Autowired
	private GeneralService generalService;

	@GetMapping("/{x}")
	public void get(@PathVariable String x) {
		System.err.println("Inside Get Controller");
		generalService.add(x);	
	}
	
}
