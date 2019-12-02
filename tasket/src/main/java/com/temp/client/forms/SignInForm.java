package com.temp.client.forms;

import com.temp.client.Client;
import com.temp.client.ClientThread;
import com.temp.common.requests.SignInRequest;
import com.temp.common.requests.SignUpRequest;
import com.temp.common.requests.params.SignInRequestParams;
import com.temp.common.requests.params.SignUpRequestParams;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignInForm extends JFrame {
    private static SignInForm instance = null;
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton signInButton;
    private JPanel signInPanel;
    private JButton signUpButton;
    private final static Logger logger = Logger.getLogger(SignInForm.class.getSimpleName());

    private SignInForm(final Client client) {
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientThread clientThread = client.getClientThread();

                String username = usernameField.getText();
                String password = passwordField.getText();

                SignUpRequestParams params = new SignUpRequestParams(username, password);
                SignUpRequest request = new SignUpRequest(params);

                try {
                    clientThread.sendRequestToServer(request);
                    SignInForm.this.setEnabled(false);

                } catch (IOException ex) {
                    logger.log(Level.SEVERE, ex.getMessage(), ex);
                    JOptionPane.showMessageDialog(SignInForm.this,
                            ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientThread clientThread = client.getClientThread();

                String username = usernameField.getText();
                String password = passwordField.getText();

                SignInRequestParams params = new SignInRequestParams(username, password);
                SignInRequest request = new SignInRequest(params);

                try {
                    clientThread.sendRequestToServer(request);
                    client.setUsername(username);
                    SignInForm.this.setEnabled(false);

                } catch (IOException ex) {
                    logger.log(Level.SEVERE, ex.getMessage(), ex);
                    JOptionPane.showMessageDialog(SignInForm.this,
                            ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        initUI();
    }

    public static SignInForm getInstance(Client client) {
        if (instance == null) {
            instance = new SignInForm(client);
        }
        return instance;
    }

    public void initUI() {
        setTitle("Sign In");
        setContentPane(signInPanel);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
    }
}
