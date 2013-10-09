package com.timgroup.saros4intellij.proxy.client;

import static java.lang.Integer.valueOf;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import com.timgroup.saros4intellij.proxy.NavigationResult;
import com.timgroup.saros4intellij.proxy.Navigator;
import com.timgroup.saros4intellij.proxy.Position;

public class HttpClientNavigator implements Navigator {
    private final String host;
    private final int port;

    public HttpClientNavigator(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public NavigationResult goTo(String filename, Position position) {
        try {
            HttpResponse response = Request.Post("http://"+host+":"+port+"/saros/location")
                   .bodyString("{ \"filename\": \"" + filename + "\", \"line\": " + position.line + ", \"column\": " + position.column + " }", 
                               ContentType.APPLICATION_JSON)
                   .execute()
                   .returnResponse();
            
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                return NavigationResult.success();
            } else {
                return NavigationResult.failed(String.valueOf(statusCode));
            }
            
        } catch (IOException ex) {
            return NavigationResult.failed(ex);
        }
    }
    
    public static void main(String[] args) {
        System.out.println(new HttpClientNavigator(args[0], valueOf(args[1])).goTo("whatever.txt", new Position(23, 45)));
    }
}
