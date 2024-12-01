package com.app.general;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.opentest4j.TestAbortedException;

public class SkipAndRepeat {
	
	@BeforeAll
	static void beforeAll() {
		System.setProperty("os.name", "Linux");
	}
	
	@Test
	@Disabled("testTwo methode is disabled for now")
	void disabledTest() {
		System.out.println("@Disabled Annotation,  is used to signal that the "
				+ "annotated test class ortest method is currently disabled and should not be executed.  ");
	}

	@Test
	void linuxDependentTestOne() {
		Assumptions.assumeTrue(System.getProperty("os.name").contains("Linux"));

		// Test will run only if the condition is true
		System.out.println("testThree - This test can only be runned on Linux");
	}

	@Test
	void linuxDependentTestTwo() {
		if (!"Linux".equals(System.getProperty("os.name"))) {
			throw new TestAbortedException("Skipping test");
		}

		// Test will run only if the os is Linux
		System.out.println("testFour - This test can only be runned on Linux");
	}
	
	
	
	@RepeatedTest(3)
	void repeatTest() {
		System.out.println("Repeat test 3 times");
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
