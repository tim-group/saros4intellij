package com.timgroup.saros4intellij.proxy;

public final class SysoutNavigator implements Navigator {
    @Override
    public Result goTo(String filename, Position position) {
        System.out.println(
            "Once Brian and Graham have done there work this will call Saros with: file: " + filename + ", position: " + position.toJson());
        return Result.success();
    }
}