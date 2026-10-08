package com.app.server;

import com.app.api.ApiHandler;
import com.app.config.AppConfig;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class AppServer {
    private final HttpServer server;

    public AppServer(AppConfig config) throws IOException {
        this.server = HttpServer.create(new InetSocketAddress(config.getServerPort()), 0);
        
        // Static file handler for frontend resources
        this.server.createContext("/", new StaticFileHandler());
        
        // API handler
        this.server.createContext("/api", new ApiHandler());
        
        this.server.setExecutor(null); // creates a default executor
    }

    public void start() {
        server.start();
    }
    
    public void stop() {
        server.stop(0);
    }
}
