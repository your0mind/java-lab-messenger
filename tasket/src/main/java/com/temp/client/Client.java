package com.temp.client;

import com.temp.client.forms.SignInForm;
import com.temp.client.forms.TestForm;
import com.temp.client.messagehandlers.MessageHandler;
import com.temp.common.Message;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private String username;
    private ClientThread clientThread = null;
    private ClientDefaultListModels defaultListModels = new ClientDefaultListModels();
    private final static Logger logger = Logger.getLogger(Client.class.getSimpleName());

    public void connectToServer(String serverHost, int serverPort) throws IOException {
        clientThread = new ClientThread(this, new Socket(serverHost, serverPort));
        clientThread.start();
    }

    public void closeConnectionToServer() throws IOException {
        clientThread.close();
    }

    public <T extends Message> void handleMessage(MessageHandler<T> handler, T message) {
        handler.handle(message, this);
    }

    public ClientThread getClientThread() {
        return clientThread;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ClientDefaultListModels getDefaultListModels() {
        return defaultListModels;
    }

    public static void main(String[] args) {
        Client client = new Client();

        try {
            client.connectToServer("localhost", 4004);
            logger.log(Level.INFO, "Connection to server successfully created");
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }

        SignInForm.getInstance(client).setVisible(true);
    }
}
