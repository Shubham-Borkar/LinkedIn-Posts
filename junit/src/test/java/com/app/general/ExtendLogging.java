package com.app.general;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtendWith;


import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

@ExtendWith(LoggingExtension.class)
public class ExtendLogging {

    @Test
    void testOne() {
        System.out.println("Executing testOne");
    }

    @Test
    void testTwo() {
        System.out.println("Executing testTwo");
    }
}


class LoggingExtension implements BeforeEachCallback, AfterEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) {
        System.out.println("Before test: " + context.getDisplayName() + " started at "+ LocalDateTime.now());
    }

    @Override
    public void afterEach(ExtensionContext context) {
        System.out.println("After test: " + context.getDisplayName()+ " ends at "+ LocalDateTime.now() + "\n");
    }
}












