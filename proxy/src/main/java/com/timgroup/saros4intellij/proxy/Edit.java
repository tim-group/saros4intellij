package com.timgroup.saros4intellij.proxy;

public class Edit {
    public final Position position;
    public final String textInserted;
    public final String textReplaced;
    
    public Edit(Position position, String textInserted, String textReplaced) {
        this.position = position;
        this.textInserted = textInserted;
        this.textReplaced = textReplaced;
    }
}
