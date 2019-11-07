package com.temp.server;

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
        connections = new LinkedList<ServerConnection>();
    }

    public void initServerSocket() {
        try {
            serverSocket = new ServerSocket(port);
            logger.log(Level.INFO, "Server started successfully");
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void closeServerSocket() {
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
                logger.log(Level.INFO, "Trying to add new connection");
                addConnection(serverSocket.accept());
                logger.log(Level.INFO, "Added new connection");
            } catch (IOException e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }

        closeServerSocket();
    }

    private void addConnection(Socket socket) throws IOException {
        ServerConnection connection = new ServerConnection(this, socket);
        connections.add(new ServerConnection(this, socket));
        connection.start();
    }

    public static void main(String[] args) {
        Server server = new Server(4004);
        server.start();

//        try {
//            Thread.sleep(10000);
//            server.interrupt();
//        } catch (InterruptedException e) {
//            logger.log(Level.SEVERE, e.getMessage(), e);
//        }
    }
}
