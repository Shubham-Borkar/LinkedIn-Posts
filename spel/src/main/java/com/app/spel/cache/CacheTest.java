package com.app.spel.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class CacheTest {

    @Autowired
    private CalculationService calculationService;
    
    @Autowired
    private CacheManager cacheManager;

    @GetMapping("/square/{input}")
    public String getSquare(@PathVariable int input) {
    	long start = System.currentTimeMillis();
    	
        int result = calculationService.calculateSquare(input);
        
        long end = System.currentTimeMillis();
        System.err.println(printCachedData(input));
        
        return "Square of " + input + " is " + result+", time to execute -> "+(end-start)+" milliseconds";
    }

    
    // Method to print cached data for specific keys   
	public String printCachedData(int key) {
		Cache calculationsCache = cacheManager.getCache("calculations");
		if (calculationsCache != null && calculationsCache.get(key) != null) {
			return "key -> "+ key + ", value -> " + calculationsCache.get(key).get();
		} else {
			return "Cache 'calculations' is empty or does not exist.";
		}
	}
}





