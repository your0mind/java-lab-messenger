package com.temp.server;

import com.google.gson.JsonObject;
import com.temp.server.requests.Request;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Thread {

    private int port;
    private LinkedList<ServerConnection> connections;
    private ServerSocket serverSocket;
    private final static Logger logger = Logger.getLogger(Server.class.getSimpleName());

    public Server(int port) {
        this.port = port;
        connections = new LinkedList<>();
    }

    private void initServerSocket() {
        try {
            serverSocket = new ServerSocket(port);
            logger.log(Level.INFO, "Server started successfully");
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void closeServerSocket() {
        try {
            serverSocket.close();
            logger.log(Level.INFO, "Server stopped successfully");
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @Override
    public void run() {
        initServerSocket();

        while (!isInterrupted()) {
            try {
                addConnection(serverSocket.accept());
            } catch (IOException e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }

        closeServerSocket();
    }

    public void addConnection(Socket socket) throws IOException {
        ServerConnection connection = new ServerConnection(this, socket);
        connections.add(new ServerConnection(this, socket));
        connection.start();

        logger.log(Level.INFO, "Added new connection");
    }

    public void removeConnection(ServerConnection serverConnection) throws IOException {
        serverConnection.close();
        connections.remove(serverConnection);

        logger.log(Level.INFO, "Connection removed");
    }

    synchronized public void handleRequest(Request request, JsonObject objectRequest) {
        request.handle(objectRequest);
    }

    public static void main(String[] args) {
        Server server = new Server(4004);
        server.start();
    }
}
