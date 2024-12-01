package com.app;

public class Tester {
	  public static void main(String[] args) {
	        // Simulate incoming web requests with different user name
	        Thread request1 = new Thread(new RequestHandler("Shubham"));
	        Thread request2 = new Thread(new RequestHandler("Vedant"));
	        Thread request3 = new Thread(new RequestHandler("Arnao"));
	        Thread request4 = new Thread(new RequestHandler(null));

	        // Start the threads
	        request1.start();
	        request2.start();
	        request3.start();
	        request4.start();
	    }
}
