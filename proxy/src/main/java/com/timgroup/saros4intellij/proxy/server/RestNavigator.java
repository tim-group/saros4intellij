package com.timgroup.saros4intellij.proxy.server;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/")
public class RestNavigator {

    @GET
    @Path("/location")
    @Produces(MediaType.APPLICATION_JSON)
    public String index(@Context HttpServletRequest request) {
        return "{ \"hello\": \"world \"}";
    }
}
