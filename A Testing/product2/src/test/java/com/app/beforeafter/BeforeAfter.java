package com.app.beforeafter;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class BeforeAfter {

	@BeforeAll
	static void beforeAll() {
		System.out.println("Before All");
	}
	
	@BeforeEach
	void beforeEach() {
		System.err.println("Before Each");
	}
	
	@Test
	void test1() {
		System.out.println("Test 1");
	}

	@Test
	void test2() {
		System.out.println("Test 2");
	}
	
	@Test
	void test3() {
		System.out.println("Test 3");
	}
	
	@AfterEach
	void afterEach() {
		System.err.println("After Each");
	}
	
	@AfterAll
	static void afterAll() {
		System.out.println("After All");
	}
}
