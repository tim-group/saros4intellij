package com.timgroup.saros4intellij.proxy.client;

import static java.lang.Integer.valueOf;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import com.timgroup.saros4intellij.proxy.Edit;
import com.timgroup.saros4intellij.proxy.Editor;
import com.timgroup.saros4intellij.proxy.Position;
import com.timgroup.saros4intellij.proxy.Result;

public class HttpClientEditor implements Editor {
    private final String host;
    private final int port;

    public HttpClientEditor(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Result edit(String filename, Edit edit) {
        try {
            HttpResponse response = Request.Post("http://"+host+":"+port+"/saros/edit")
                   .bodyString("{ \"filename\": \"" + filename + "\", " +
                   		         "\"offset\": " + edit.position.offset + ", " +
                   		         "\"inserted\": \"" + edit.textInserted + "\", " +
                   		         "\"replaced\": \"" + edit.textReplaced + "\"" +
                   		         " }", 
                               ContentType.APPLICATION_JSON)
                   .execute()
                   .returnResponse();
            
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                return Result.success();
            } else {
                return Result.failed(String.valueOf(statusCode));
            }
            
        } catch (IOException ex) {
            return Result.failed(ex);
        }
    }
    
    public static void main(String[] args) {
        System.out.println(new HttpClientEditor(args[0], valueOf(args[1])).edit("whatever.txt", new Edit(new Position(23), "in", "out")));
    }
}
