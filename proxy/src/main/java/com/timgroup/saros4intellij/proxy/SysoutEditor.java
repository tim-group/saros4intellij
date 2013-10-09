package com.timgroup.saros4intellij.proxy;

public class SysoutEditor implements Editor {
    @Override
    public Result edit(String filename, Edit edit) {
        System.out.println("Should edit " + filename);
        return Result.success();
    }
}
