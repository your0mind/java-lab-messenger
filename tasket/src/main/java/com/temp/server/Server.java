package com.temp.server;

import com.temp.model.models.User;
import com.temp.server.requests.Request;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Thread {

    private int port;
    private LinkedList<ServerThread> threads;
    private ServerSocket serverSocket;
    private final static Logger logger = Logger.getLogger(Server.class.getSimpleName());

    public Server(int port) {
        this.port = port;
        threads = new LinkedList<>();
    }

    private void initServerSocket() {
        try {
            serverSocket = new ServerSocket(port);
            logger.log(Level.INFO, "Server started successfully");
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void closeServerSocket() {
        try {
            serverSocket.close();
            logger.log(Level.INFO, "Server stopped successfully");
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @Override
    public void run() {
        initServerSocket();

        while (!isInterrupted()) {
            try {
                addThread(serverSocket.accept());
            }
            catch (IOException e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }

        closeServerSocket();
    }

    public void addThread(Socket socket) throws IOException {
        ServerThread connection = new ServerThread(this, socket);
        threads.add(new ServerThread(this, socket));
        connection.start();

        logger.log(Level.INFO, "Added new connection");
    }

    public void removeThread(ServerThread serverThread) throws IOException {
        serverThread.close();
        threads.remove(serverThread);

        logger.log(Level.INFO, "Connection removed");
    }

    synchronized public void handleRequest(User requester, Request request, String params) {
        request.handle(requester, params);
    }

    public static void main(String[] args) {
        Server server = new Server(4004);
        server.start();
    }
}
