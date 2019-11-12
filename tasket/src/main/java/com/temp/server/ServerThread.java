package com.temp.server;

import com.temp.common.Message;
import com.temp.server.requests.Request;
import com.temp.server.requests.RequestBuilder;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.security.InvalidParameterException;
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

        inputStream = new ObjectInputStream(socket.getInputStream());
        outputStream = new ObjectOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Message message = (Message) inputStream.readObject();
                Request reqToHandle = RequestBuilder.build(message.requestInfo);
                server.handleRequest(message.user, reqToHandle, message.requestInfo.params);
                logger.log(Level.INFO, "Request was handled");
            }
            catch (SocketException e) {
                interrupt();
                logger.log(Level.INFO, "ServerThread was closed by user");
            }
            catch (ClassNotFoundException e) {
                //TODO: отправка юзеру сообщения с ошибкой
                logger.log(Level.SEVERE, "Invalid message from user");
            }
            catch (InvalidParameterException e) {
                //TODO: отправка юзеру сообщения с ошибкой
                logger.log(Level.SEVERE, "Invalid request's information from user");
            }
            catch (IOException e) {
                interrupt();
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }

        try {
            server.removeThread(this);
            logger.log(Level.INFO, "ServerThread was removed from list");
        }
        catch (IOException e) {
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
