package com.temp.client;

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
    private static Client instance = null;
    private User user;
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private final static Logger logger = Logger.getLogger(Client.class.getSimpleName());

    private Client() {};

    public static Client getInstance() {
        if (instance == null) {
            instance = new Client();
        }

        return instance;
    }

    public void connectToServer(String serverHost, int serverPort) throws IOException {
        this.socket = new Socket(serverHost, serverPort);

        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
    }

    public void closeConnectionToServer() throws IOException {
        socket.close();
        outputStream.close();
        inputStream.close();
    }

    public Response sendRequestToServer(Request request) throws IOException, ClassNotFoundException {
        outputStream.writeObject(request);
        return (Response) inputStream.readObject();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

        new SignInForm().setVisible(true);
    }
}
