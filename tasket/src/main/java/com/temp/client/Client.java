package com.temp.client;

import com.temp.client.forms.SignInForm;
import com.temp.server.Server;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    private Socket socket;
    private final static Logger logger = Logger.getLogger(Server.class.getSimpleName());


    private void Client() {
    }

    private void createConnection(String host, int port) throws IOException {
        socket = new Socket(host, port);
    }

    public static void main(String[] args) {
        Client client = new Client();

        try {
            client.createConnection("localhost", 4004);
            logger.log(Level.INFO, "Connection to server successfully created");

            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);

        }

        new SignInForm().setVisible(true);
    }
}
