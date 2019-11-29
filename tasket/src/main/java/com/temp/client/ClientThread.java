package com.temp.client;

import com.temp.client.forms.SignInForm;
import com.temp.client.messagehandlers.MessageHandler;
import com.temp.client.messagehandlers.MessageHandlerBuilder;
import com.temp.common.Message;
import com.temp.common.requests.Request;

import javax.swing.*;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientThread extends Thread implements Closeable {
    private Client client;
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public ClientThread(Client client, Socket socket) throws IOException {
        this.client = client;
        this.socket = socket;

        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                Message msg = receiveMessageFromServer();
                MessageHandler handler = MessageHandlerBuilder.build(msg);
                client.handleMessage(handler, msg);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private Message receiveMessageFromServer() throws IOException, ClassNotFoundException {
        return (Message) inputStream.readObject();
    }

    public synchronized void sendRequestToServer(Request request) throws IOException {
        outputStream.writeObject(request);
    }

    @Override
    public void close() throws IOException {
        socket.close();
        outputStream.close();
        inputStream.close();
    }
}
