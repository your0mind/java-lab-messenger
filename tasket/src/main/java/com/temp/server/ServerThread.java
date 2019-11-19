package com.temp.server;

import com.temp.common.requests.Request;
import com.temp.common.responses.ErrorResponse;
import com.temp.common.responses.Response;
import com.temp.server.exceptions.UnknownRequestException;
import com.temp.server.requests.RequestHandlerBuilder;
import com.temp.server.requests.handlers.RequestHandler;

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
    private final static Logger logger = Logger.getLogger(ServerThread.class.getSimpleName());

    public ServerThread(Server server, Socket socket) throws IOException {
        this.server = server;
        this.socket = socket;

        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Request request = receiveRequest();
                RequestHandler handler = RequestHandlerBuilder.build(request);
                sendResponse(server.handleRequest(handler, request));
                logger.log(Level.INFO, request.getClass().getSimpleName() + " was handled");

            } catch (ClassNotFoundException | UnknownRequestException e) {
                try {
                    sendResponse(new ErrorResponse(e.getMessage()));
                    logger.log(Level.SEVERE, e.getMessage());

                } catch (IOException ex) {
                    logger.log(Level.SEVERE, e.getMessage(), e);
                }

            } catch (SocketException e) {
                finish();
                logger.log(Level.INFO, "ServerThread was closed by user");

            } catch (IOException e) {
                finish();
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }

    private Request receiveRequest() throws IOException, ClassNotFoundException {
        return (Request) inputStream.readObject();
    }

    private void sendResponse(Response response) throws IOException {
        outputStream.writeObject(response);
    }

    private void finish() {
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
}
