package com.temp.server;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerConnection extends Thread implements Closeable {

    private Server server;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private final static Logger logger = Logger.getLogger(ServerConnection.class.getSimpleName());

    public ServerConnection(Server server, Socket socket) throws IOException {
        this.server = server;
        this.socket = socket;

        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                String message = reader.readLine();
            } catch (SocketException e) {
                logger.log(Level.INFO, "Connection was closed by client");

                try {
                    server.removeConnection(this);
                } catch (IOException ex) {
                    logger.log(Level.SEVERE, e.getMessage(), e);
                }

                interrupt();
            } catch (IOException e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }

        logger.log(Level.INFO, "ServerConnection thread finished");
    }

    @Override
    public void close() throws IOException {
        socket.close();
        reader.close();
        writer.close();
    }
}
