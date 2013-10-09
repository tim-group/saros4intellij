package com.timgroup.saros4intellij.proxy.server;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class RestServlet extends ServletContainer {
    private static final long serialVersionUID = 1L;

    public static ServletContainer create() {
        ResourceConfig config = new ResourceConfig();

        config.registerInstances(new LocationResource(null));

        return new ServletContainer(config);
    }

}
