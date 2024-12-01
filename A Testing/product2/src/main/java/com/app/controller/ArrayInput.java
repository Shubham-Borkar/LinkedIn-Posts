package com.app.controller;



import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test-array")
public class ArrayInput {

	 @PostMapping("/array-input/{userid}/{a}/{b}/{c}")
	    public void testone(@PathVariable(name = "userid") int userid,
	    					@PathVariable(name = "a") int a, 
	                        @PathVariable(name = "b") int b,
	                        @PathVariable(name = "c") int c) {
//		 List<Integer> nonZeroList = new ArrayList<>();
//		 if (a != 0) {nonZeroList.add(a);}
//	     if (b != 0) {nonZeroList.add(b);}
//	     if (c != 0) {nonZeroList.add(c);}
//	     int[] data = nonZeroList.stream().mapToInt(Integer::intValue).toArray();
//	     //call methode using data and userid hh
//	     System.err.println(Arrays.toString(data)+Integer.toString(userid));
	 }

}
