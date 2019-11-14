package com.temp.server;

import com.temp.common.MessageToServer;
import com.temp.common.requests.RequestParams;
import com.temp.model.models.User;
import com.temp.server.requests.Request;
import com.temp.server.requests.RequestBuilder;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Thread {
    private int port;
    private ServerSocket serverSocket;
    private LinkedList<ServerThread> threads;
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
                logger.log(Level.INFO, "Added new ServerThread");
            }
            catch (IOException e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }

        closeServerSocket();
    }

    public void addThread(Socket socket) throws IOException {
        ServerThread serverThread = new ServerThread(this, socket);
        threads.add(serverThread);
        serverThread.start();
    }

    public void removeThread(ServerThread serverThread) throws IOException {
        serverThread.close();
        threads.remove(serverThread);
    }

    synchronized public void handleRequest(User requester, Request request, RequestParams params) {
        request.handle(requester, params);
    }

    public static void main(String[] args) {
        Server server = new Server(4004);
        server.start();
    }
}
