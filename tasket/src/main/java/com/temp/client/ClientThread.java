package com.temp.client;

import com.temp.common.Message;
import com.temp.common.requests.Request;
import com.temp.common.responses.Response;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientThread extends Thread implements Closeable {
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private final static Logger logger = Logger.getLogger(ClientThread.class.getSimpleName());

    public ClientThread(Socket socket) throws IOException {
        this.socket = socket;

        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                Message msg = receiveMessageFromServer();
            }

        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void connectToServer(String serverHost, int serverPort) throws IOException {
        this.socket = new Socket(serverHost, serverPort);

        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
    }

    public Message receiveMessageFromServer() throws IOException, ClassNotFoundException {
        return (Message) inputStream.readObject();
    }

    public Response sendRequestToServer(Request request) throws IOException, ClassNotFoundException {
        outputStream.writeObject(request);
        return (Response) inputStream.readObject();
    }

    @Override
    public void close() throws IOException {
        socket.close();
        outputStream.close();
        inputStream.close();
    }
}
