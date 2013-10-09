package com.timgroup.saros4intellij;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Writer {
    public static void write(String message) {
        System.out.println(message);
        try {
            PrintWriter w = new PrintWriter(new FileWriter(System.getProperty("user.home") + "/saros4intellij_output.txt", true));
            w.println(message);
            w.flush();
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
