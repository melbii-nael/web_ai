package com.app.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class StaticFileHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!"GET".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }

        String path = exchange.getRequestURI().getPath();
        if (path.equals("/")) {
            path = "/index.html";
        }

        // Prevent directory traversal
        if (path.contains("..")) {
            exchange.sendResponseHeaders(403, -1);
            return;
        }

        URL resource = getClass().getResource("/public" + path);
        if (resource == null) {
            exchange.sendResponseHeaders(404, -1);
            return;
        }

        byte[] content;
        try (InputStream is = resource.openStream()) {
            content = is.readAllBytes();
        }

        String contentType = getContentType(path);
        exchange.getResponseHeaders().set("Content-Type", contentType);
        exchange.sendResponseHeaders(200, content.length);
        
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(content);
        }
    }

    private String getContentType(String path) {
        if (path.endsWith(".html")) return "text/html; charset=UTF-8";
        if (path.endsWith(".css")) return "text/css; charset=UTF-8";
        if (path.endsWith(".js")) return "application/javascript; charset=UTF-8";
        return "application/octet-stream";
    }
}
