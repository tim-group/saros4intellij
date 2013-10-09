package com.timgroup.saros4intellij.proxy;

public class Position {
    public final int offset;
    
    public Position(int offset) {
        this.offset = offset;
    }
    
    public String toJson() {
        return "{\"offset\": " + offset + " }";
    }
}
