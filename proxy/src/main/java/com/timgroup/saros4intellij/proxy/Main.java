package com.timgroup.saros4intellij.proxy;

import com.timgroup.saros4intellij.proxy.server.RestService;


public class Main {
    public static void main(String[] args) {
        new RestService(new SysoutNavigator()).start();
    }
}
