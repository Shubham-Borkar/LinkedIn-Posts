package com.app.general;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BeforeAfter {
	public static int counter = 0;

	@BeforeAll
	static void beforeAll() {
		System.out.println("@BeforeAll - is used to signal that the annotated method "
				+ "should be executed before all tests in the current test class. \n\n");
	}

	@BeforeEach
	void beforeEach() {
		System.err.println("@BeforeEach - is used to signal that the annotated method"
				+ " should be executed before each Test, ");
	}

	@Test
	void testOne() {
		System.out.println("Test Methode 1, @Test - is used to signal that the " 
				+ "annotated method is a test method. ");
	}

	@Test
	@DisplayName("Test Two with @DisplayName - is used to declare a custom displayname for "
				+ "the annotated test class or test method. ")
	void testTwo() {
		System.out.println("Test Methode 1, @Test - is used to signal that the " 
				+ "annotated method is a test method. ");
	}

	@AfterEach
	void afterEach() {
		System.err.println("@AfterEach - is used to signal that the annotated "
				+ "method should be executed after each Test \n");
	}

	@AfterAll
	static void afterAll() {
		System.out.println("\n@AfterAll is used to signal that the annotated method"
				+ " should be executed after all tests in the current test class. ");
	}
}

















