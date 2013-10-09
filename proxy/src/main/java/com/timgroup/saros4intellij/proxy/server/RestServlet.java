package com.timgroup.saros4intellij.proxy.server;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import com.timgroup.saros4intellij.proxy.Navigator;

public class RestServlet extends ServletContainer {
    private static final long serialVersionUID = 1L;

    public static ServletContainer create(Navigator listener) {
        ResourceConfig config = new ResourceConfig();

        config.registerInstances(new LocationResource(listener));

        return new ServletContainer(config);
    }

}
