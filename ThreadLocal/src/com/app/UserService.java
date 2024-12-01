package com.app;

public class UserService {
	
    public void processUser() {
    	
        String userName = ThreadDataHolder.getUserName();
        
        if (userName != null) {
            System.out.println(Thread.currentThread().getName() 
            				   + " Processing request for user name: " + userName);
        } else {
            System.out.println(Thread.currentThread().getName() 
            					+ " don't have user name associated with thread-local variable");
        }
    }
}
