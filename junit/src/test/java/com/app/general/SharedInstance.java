package com.app.general;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

// By default, JUnit 5 creates a new instance of the test class for each test method. 
// However, when @TestInstance(TestInstance.Lifecycle.PER_CLASS) is used, JUnit 
// creates only one instance of the test class, and this single instance is shared 
// across all test methods in the class.
// Effect: Because there is only one instance of the test class, 
// any state (such as instance variables) will be shared across all test methods.

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SharedInstance {
    private int counter = 0;

    @Test
    void testOne() {
        counter++;
        System.out.println("Test One: " + counter);
    }

    @Test
    void testTwo() {
        counter++;
        System.out.println("Test Two: " + counter);
    }
    
    

}
