package com.temp.client;

import com.temp.client.forms.MainForm;
import com.temp.client.forms.SignInForm;
import com.temp.common.requests.Request;
import com.temp.common.responses.Response;
import com.temp.model.models.User;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private String username;
    private static Client instance = null;
    private ClientThread clientThread = null;
    private final static Logger logger = Logger.getLogger(Client.class.getSimpleName());

    private Client() {};

    public static Client getInstance() {
        if (instance == null) {
            instance = new Client();
        }

        return instance;
    }

    public void connectToServer(String serverHost, int serverPort) throws IOException {
        clientThread = new ClientThread(new Socket(serverHost, serverPort));
    }

    public void closeConnectionToServer() throws IOException {
        clientThread.close();
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

    public static void main(String[] args) {
        Client client = Client.getInstance();

        try {
//            client.connectToServer("localhost", 4004);
            logger.log(Level.INFO, "Connection to server successfully created");
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }

        new MainForm().setVisible(true);
    }
}
