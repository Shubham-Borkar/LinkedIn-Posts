package com.app;

public class RequestHandler implements Runnable {
	
    private final String userName;

    public RequestHandler(String userName) {
        this.userName = userName;
    }
 
    @Override
    public void run() {
        try {
            // Set the user name in the current thread local variable
            ThreadDataHolder.setUserName(userName);

            // Simulate processing the request
            UserService userService = new UserService();
            userService.processUser();
            
        } finally {
            // Clear the thread local variable value
        	ThreadDataHolder.clear();
        }
    }
}


