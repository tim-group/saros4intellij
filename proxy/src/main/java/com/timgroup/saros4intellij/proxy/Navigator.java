package com.timgroup.saros4intellij.proxy;

public interface Navigator {
    NavigationResult goTo(String filename, Position position);
}
