package com.temp.server;

import com.temp.common.requests.Request;
import com.temp.common.responses.Response;
import com.temp.model.models.Dialog;
import com.temp.model.services.DialogService;
import com.temp.model.services.impl.DialogServiceImpl;
import com.temp.server.requesthandlers.RequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Thread {
    private int port;
    private ServerSocket serverSocket;
    private LinkedList<ServerThread> threads = new LinkedList<>();;
    private final static Logger logger = Logger.getLogger(Server.class.getSimpleName());

    public Server(int port) {
        this.port = port;
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
                addThread(serverSocket.accept());
                logger.log(Level.INFO, "Added new ServerThread");

            } catch (IOException e) {
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

    synchronized public Response handleRequest(RequestHandler handler, Request request, ServerThread callerThread) {
        return handler.handle(request, callerThread, threads);
    }

    public static void main(String[] args) {
        Server server = new Server(4004);
        server.start();
    }
}
