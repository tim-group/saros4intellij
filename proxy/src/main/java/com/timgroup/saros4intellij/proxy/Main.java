package com.timgroup.saros4intellij.proxy;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.timgroup.saros4intellij.proxy.server.RestServlet;

public class Main {

    public static void main(String[] args) {
        Server server = createServer();
        try {
            server.start();
            System.out.println("Running Saros REST Server on port " + 7373);
            server.join();
        } catch (Exception e) {
            System.err.println("Died: " + e.getMessage());
        }
    }

    private static Server createServer() {
        Server server = new Server(7373);
        
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] {
                transactionsContext(server)
        });

        server.setHandler(handlers);
        return server;
    }
    
    private static ContextHandler transactionsContext(Server server) {
        ServletContextHandler servletContext = new ServletContextHandler(
                server, "/", ServletContextHandler.SESSIONS | ServletContextHandler.SECURITY);

        servletContext.addServlet(new ServletHolder(RestServlet.create()), "/saros/*");

        return servletContext;
    }
}
