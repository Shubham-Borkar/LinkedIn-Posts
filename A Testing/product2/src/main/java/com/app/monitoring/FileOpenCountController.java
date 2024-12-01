package com.app.monitoring;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
public class FileOpenCountController {

//    @GetMapping("/file-open-count")
//    public NativeLong getFileOpenCountApi() {
//    	return FileDescriptorUtil.getFileOpenCount();        
//    }
	@GetMapping("/increaseBy/{value}")
	public void increaseBy(@PathVariable int value) {
		 
	        try {
	        	
	        	 for (int i = 0; i < value; i++) {
	        	String fileName = "example"+i+".txt";
	        	
	            File file = new File(fileName);
	            FileWriter writer = new FileWriter(file);
	            writer.write("Hello abc");
	            System.out.println("File count"+i);
	            
	        	 }
	        	 
	        } catch (IOException ie) {
		        System.err.println(ie.getMessage());
		    } catch (Exception ex) {
		    	  System.err.println("Un-Expected Error");
		    }
			
         }
	     
	@GetMapping("/increaseByBR/{value}")
	 public void leakResource(@PathVariable int value) {
		
	       
	        BufferedReader reader = null;
	        try {
	        	for (int i = 0; i < value; i++) {
	        		String fileName = "example"+i+".txt";
		            File file = new File(fileName);
		            FileWriter writer = new FileWriter(file);
		            writer.write("Hello abc");
		            System.out.println("File count"+i);
		            writer.close();
		            
	            reader = new BufferedReader(new FileReader(file));
	            String line;
	            while ((line = reader.readLine()) != null) {
	                // Do something with the line if needed
	                System.out.println(line);
	            }
	        	}
	        } catch (IOException e) {
	        	 System.err.println(e.getMessage());
	        }  catch (Exception ex) {
		    	  System.err.println("Un-Expected Error");
		    }
	    }

	
}