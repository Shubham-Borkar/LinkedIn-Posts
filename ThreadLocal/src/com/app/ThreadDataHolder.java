package com.app;

public class ThreadDataHolder {
	
	private static final ThreadLocal<String> userName = ThreadLocal.withInitial(() -> null);

    public static void setUserName(String session) {
        userName.set(session);
    }

    public static String getUserName() {
        return userName.get();
    }

    public static void clear() {
        userName.remove();
    }
}














