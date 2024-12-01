package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableCaching
public class Application {

	public static void main(String[] args) {
		System.setProperty("user.name", "Shubham");
		//System.setProperty("server.name", "S_V_101");

		System.err.println("Setting System Property 'user.name' and 'server.name'");

		SpringApplication.run(Application.class, args);
	}

}
