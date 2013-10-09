package com.timgroup.saros4intellij.proxy.server;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.timgroup.saros4intellij.proxy.Edit;
import com.timgroup.saros4intellij.proxy.Editor;
import com.timgroup.saros4intellij.proxy.Position;

@Path("/")
public class EditResource {
    private final Editor listener;

    public EditResource(Editor listener) {
        this.listener = listener;
    }

    @POST
    @Path("/edit")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String index(@Context HttpServletRequest request, JSONObject postBody) throws JSONException {
        String filename = postBody.getString("filename");
        int offset = postBody.getInt("offset");
        String inserted = postBody.getString("inserted");
        String replaced = postBody.getString("replaced");
        
        listener.edit(filename, new Edit(new Position(offset), inserted, replaced));
        
        return "{ \"success\": true}";
    }
}
