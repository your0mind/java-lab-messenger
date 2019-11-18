package com.temp.server;

import com.temp.common.MessageToClient;
import com.temp.common.MessageToServer;
import com.temp.model.models.User;
import com.temp.server.exceptions.InvalidRequestParamsException;
import com.temp.server.exceptions.UnknownRequestException;
import com.temp.server.requests.LoginRequest;
import com.temp.server.requests.Request;
import com.temp.server.requests.RequestBuilder;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerThread extends Thread implements Closeable {
    private User user = null;
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
                MessageToServer msg = receiveMessage();
                Request request = RequestBuilder.build(msg.requestInfo);
                MessageToClient msgToClient = server.createResponseMessage(msg.user, request, msg.requestInfo.params);

                // запоминание юзера в текущем потоке общения
                if (request instanceof LoginRequest && msgToClient.error.isEmpty()) {
                    user = msg.user;
                    user.id = Integer.parseInt(msgToClient.text);
                }

                sendMessage(msgToClient);
                logger.log(Level.INFO, "MessageToServer was handled");

            } catch (ClassNotFoundException | UnknownRequestException | InvalidRequestParamsException |
                    InstantiationException | IllegalAccessException e) {
                try {
                    sendMessage(new MessageToClient(null, e.getMessage()));
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

    private MessageToServer receiveMessage() throws IOException, ClassNotFoundException {
        return (MessageToServer) inputStream.readObject();
    }

    private void sendMessage(MessageToClient message) throws IOException {
        outputStream.writeObject(message);
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
