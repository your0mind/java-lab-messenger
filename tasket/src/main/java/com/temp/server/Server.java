package com.temp.server;

import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Thread {

    private int port;
    private ServerSocket serverSocket;
    private final static Logger logger = Logger.getLogger(Server.class.getSimpleName());

    public Server(int port) {
        this.port = port;
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

        }

        closeServerSocket();
    }

    public static void main(String[] args) {
        Server server = new Server(4004);
        server.start();

        try {
            Thread.sleep(10000);
            server.interrupt();
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
