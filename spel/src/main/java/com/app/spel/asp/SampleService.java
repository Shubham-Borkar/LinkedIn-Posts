package com.app.spel.asp;

import org.springframework.stereotype.Component;

@Component
public class SampleService {

    public void someMethod(String name) {
        System.err.println("Executing someMethod with name: " + name);
    }
    public void someMethod(String arg1, String arg2) {
        System.err.println("Executing someMethod with 2 args: " + arg1 +", "+ arg2);
    }
}