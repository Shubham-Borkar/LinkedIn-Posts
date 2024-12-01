package com.app.spel.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CalculationService {

    @Cacheable(value = "calculations", key = "#input", condition = "#input > 0")
    public int calculateSquare(int input) {
    	System.err.println("calculateSquare Called");
        try {
            // Simulating a time-consuming calculation
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return input * input;
    }
}
