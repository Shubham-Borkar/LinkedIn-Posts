package com.javad;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.javad.config.MyBanner;

@SpringBootApplication
public class JavadApplication implements CommandLineRunner{

	public static void main(String[] args) {
		//SpringApplication.run(JSApplication.class, args);
		SpringApplication app =new SpringApplication(JavadApplication.class);	
		app.setBanner(new MyBanner());
		app.run(args);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void run(String... args) throws Exception {
	List<String> nonOptionArgs = arguments.getNonOptionArgs();
	System.err.println(nonOptionArgs);
	}
	
	@Autowired
	ApplicationArguments arguments;

}



//app.setBannerMode(Banner.Mode.OFF);
