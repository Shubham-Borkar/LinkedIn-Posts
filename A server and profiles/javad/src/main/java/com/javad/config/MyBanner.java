package com.javad.config;

import java.io.PrintStream;

import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

public class MyBanner implements Banner{

	@Override
	public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
	    String appName = environment.getProperty("spring.application.name", "MyApp");
        String appVersion = environment.getProperty("app.version", "1.0.0");
        out.println("####################################");
        out.println("#                                  #");
        out.println("#       Welcome to MyApp           #");
        out.println("#                                  #");
        out.println("####################################");
        out.println("   App Name " + appName + " ");
        out.println("   Version: " + appVersion + "");
        out.println("   Source: " + sourceClass.getSimpleName() + "  ");
	}
	
}
