package com.cache;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cache.pojo.Product;
import com.cache.repository.ProductRepository;
import com.cache.spell.SpelDemo;

@SpringBootApplication
//@EnableCaching
public class CacheApplication implements CommandLineRunner{
	@Autowired
	private ProductRepository prepo;
	
	public static void main(String[] args) {
		SpringApplication.run(CacheApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		
	}


}
