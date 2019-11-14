package com.temp.server;

import com.temp.common.MessageToServer;
import com.temp.server.exceptions.InvalidRequestParamsException;
import com.temp.server.exceptions.UnknownRequestException;
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

        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                MessageToServer message = (MessageToServer) inputStream.readObject();
                Request request = RequestBuilder.build(message.requestInfo);
                server.handleRequest(message.user, request, message.requestInfo.params);
                logger.log(Level.INFO, "Message was handled");
            }
            catch (ClassNotFoundException e) {
                //TODO: отправка юзеру сообщения с ошибкой
                logger.log(Level.SEVERE, "Invalid input object type from user");
            }
            catch (UnknownRequestException | InvalidRequestParamsException |
                    InstantiationException | IllegalAccessException e) {
                //TODO: отправка юзеру сообщения с ошибкой
                logger.log(Level.SEVERE, e.getMessage());
            }
            catch (SocketException e) {
                finish();
                logger.log(Level.INFO, "ServerThread was closed by user");
            }
            catch (IOException e) {
                finish();
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }

    public void finish() {
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
