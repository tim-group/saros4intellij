package com.timgroup.saros4intellij.proxy;

public class Position {
    public final int line;
    public final int column;
    
    public Position(int line, int column) {
        super();
        this.line = line;
        this.column = column;
    }
    
    public String toJson() {
        return "{\"line\": " + line + ", \"column\": " + column + "}";
    }
}
