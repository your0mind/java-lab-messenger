package com.temp.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerConnection extends Thread {

    public Server server;
    public Socket socket;
    public ObjectInputStream streamIn;
    public ObjectOutputStream streamOut;
    private final static Logger logger = Logger.getLogger(Server.class.getSimpleName());

    public ServerConnection(Server server, Socket socket) throws IOException {
        super();
        this.server = server;
        this.socket = socket;

        streamIn = new ObjectInputStream(socket.getInputStream());
        streamOut = new ObjectOutputStream(socket.getOutputStream());
        streamOut.flush();
    }

    @Override
    public void run() {

    }
}
