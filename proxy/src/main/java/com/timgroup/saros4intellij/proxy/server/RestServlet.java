package com.timgroup.saros4intellij.proxy.server;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import com.timgroup.saros4intellij.proxy.Editor;
import com.timgroup.saros4intellij.proxy.Navigator;

public class RestServlet extends ServletContainer {
    private static final long serialVersionUID = 1L;

    public static ServletContainer create(Navigator navigator, Editor editor) {
        ResourceConfig config = new ResourceConfig();

        config.registerInstances(new LocationResource(navigator), new EditResource(editor));

        return new ServletContainer(config);
    }

}
