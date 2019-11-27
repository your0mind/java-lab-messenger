package com.temp.server;

import com.temp.common.Message;
import com.temp.common.requests.Request;
import com.temp.common.responses.SignInResponse;
import com.temp.common.responses.Response;
import com.temp.server.exceptions.UnknownRequestException;
import com.temp.server.requesthandlers.RequestHandlerBuilder;
import com.temp.server.requesthandlers.RequestHandler;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerThread extends Thread implements Closeable {
    private Server server;
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private UserSessionInfo userSessionInfo = new UserSessionInfo();
    private final static Logger logger = Logger.getLogger(ServerThread.class.getSimpleName());

    public ServerThread(Server server, Socket socket) throws IOException {
        this.server = server;
        this.socket = socket;

        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                Request request = receiveRequest();
                RequestHandler handler = RequestHandlerBuilder.build(request);
                Response response = server.handleRequest(handler, request, this);
                sendMessage(response);
                logger.log(Level.INFO, request.getClass().getSimpleName() + " was handled");
            }

        } catch (SocketException e) {
            logger.log(Level.INFO, "ServerThread's connection was closed by user");

        } catch (IOException | ClassNotFoundException | UnknownRequestException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);

        } finally {
            finish();
        }
    }

    private Request receiveRequest() throws IOException, ClassNotFoundException {
        return (Request) inputStream.readObject();
    }

    public synchronized void sendMessage(Message message) throws IOException {
        outputStream.writeObject(message);
    }

    public void finish() {
        interrupt();

        try {
            server.removeThread(this);
            logger.log(Level.INFO, "ServerThread was removed from list");

        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }

        logger.log(Level.INFO, "ServerThread finished");
    }

    @Override
    public void close() throws IOException {
        socket.close();
        inputStream.close();
        outputStream.close();
    }

    public UserSessionInfo getUserSessionInfo() {
        return userSessionInfo;
    }
}
