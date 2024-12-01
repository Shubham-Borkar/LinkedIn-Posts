package com.cache.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class GeneralService {
	
	@Cacheable(cacheNames = "rproduct",key = "#x")
	public String add(String x) {
		System.err.println("Add General Service");
		return x;
	}

}
