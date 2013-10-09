package com.timgroup.saros4intellij.proxy;

public class NavigationResult {
    public final boolean succeeded;
    public final String message;
    public final Exception exception;
    
    private NavigationResult(boolean succeeded, String message, Exception exception) {
        this.succeeded = succeeded;
        this.message = message;
        this.exception = exception;
    }
    
    public static NavigationResult success() {
        return new NavigationResult(true, null, null);
    }
}
