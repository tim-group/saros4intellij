package com.timgroup.saros4intellij.proxy.server;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.timgroup.saros4intellij.proxy.Editor;
import com.timgroup.saros4intellij.proxy.Navigator;

public class RestService {
    private final Navigator navigationListener;
    private final Editor editListener;

    public RestService(Navigator navigationListener, Editor editListener) {
        this.navigationListener = navigationListener;
        this.editListener = editListener;
    }
    
    public void start() {
        Server server = createServer(navigationListener, editListener);
        try {
            server.start();
            System.out.println("Running Saros REST Server on port " + 7373);
            server.join();
        } catch (Exception e) {
            System.err.println("Died: " + e.getMessage());
        }
    }

    private static Server createServer(Navigator navigationListener, Editor editListener) {
        Server server = new Server(7373);
        
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] {
                transactionsContext(server, navigationListener, editListener)
        });

        server.setHandler(handlers);
        return server;
    }
    
    private static ContextHandler transactionsContext(Server server, Navigator navigationListener, Editor editListener) {
        ServletContextHandler servletContext = new ServletContextHandler(
                server, "/", ServletContextHandler.SESSIONS | ServletContextHandler.SECURITY);

        servletContext.addServlet(new ServletHolder(RestServlet.create(navigationListener, editListener)), "/saros/*");

        return servletContext;
    }
}
