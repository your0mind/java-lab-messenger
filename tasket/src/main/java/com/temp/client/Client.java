package com.temp.client;

import com.temp.client.forms.MainForm;
import com.temp.client.forms.SignInForm;
import com.temp.client.messagehandlers.MessageHandler;
import com.temp.common.Message;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private SignInForm signInForm;
    private MainForm mainForm;

    private String username;
    private static Client instance = null;
    private ClientThread clientThread = null;
    private final static Logger logger = Logger.getLogger(Client.class.getSimpleName());

    private Client() {
        signInForm = new SignInForm();
        signInForm.setVisible(true);

//        mainForm = new MainForm();
//        mainForm.setVisible(true);
    }

    public static Client getInstance() {
        if (instance == null) {
            instance = new Client();
        }
        return instance;
    }

    public void connectToServer(String serverHost, int serverPort) throws IOException {
        clientThread = new ClientThread(this, new Socket(serverHost, serverPort));
        clientThread.start();
    }

    public void closeConnectionToServer() throws IOException {
        clientThread.close();
    }

    public void handleMessage(MessageHandler handler, Message message) {
        handler.handle(message);
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


    public SignInForm getSignInForm() {
        return signInForm;
    }

    public void setSignInForm(SignInForm signInForm) {
        this.signInForm = signInForm;
    }

    public MainForm getMainForm() {
        return mainForm;
    }

    public void setMainForm(MainForm mainForm) {
        this.mainForm = mainForm;
    }

    public static void main(String[] args) {
        Client client = Client.getInstance();

        try {
            client.connectToServer("localhost", 4004);
            logger.log(Level.INFO, "Connection to server successfully created");
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
