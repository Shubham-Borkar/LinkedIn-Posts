package com.app.spel.asp;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AppAspect {

	@Before("execution(* com.app.spel.asp..*(..)) && args(name)")
	public void logMethodCall(JoinPoint joinPoint, String name) {
		System.err.println("Aspect @Before -> Method called with argument: " + name);
	}

}
