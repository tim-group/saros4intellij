package com.timgroup.saros4intellij.proxy;


public class Result {
    public final boolean succeeded;
    public final String message;
    public final Exception exception;
    
    private Result(boolean succeeded, String message, Exception exception) {
        this.succeeded = succeeded;
        this.message = message;
        this.exception = exception;
    }
    
    @Override
    public String toString() {
        return "Result: succeeded: " + succeeded + ", message: " + message + ", exception: " + exception;
    }
    
    public static Result success() {
        return new Result(true, null, null);
    }

    public static Result failed(Exception ex) {
        return new Result(false, ex.getMessage(), ex);
    }

    public static Result failed(String message) {
        return new Result(false, message, null);
    }
}
