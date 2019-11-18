package com.temp.client.forms;

import com.temp.client.Client;
import com.temp.common.MessageToClient;
import com.temp.common.MessageToServer;
import com.temp.common.requests.LoginRequestParams;
import com.temp.common.requests.RequestInfo;
import com.temp.common.requests.RequestParams;
import com.temp.model.models.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignInForm extends JFrame {
    private JTextField loginField;
    private JTextField passwordField;
    private JCheckBox registerCheckBox;
    private JButton signInButton;
    private JPanel signInPanel;
    private Client client;
    private final static Logger logger = Logger.getLogger(SignInForm.class.getSimpleName());

    public SignInForm() {
        client = Client.getInstance();

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    client.connectToServer("localhost", 4004);
                    logger.log(Level.INFO, "Connection to server successfully created");

                    RequestParams requestParams = new LoginRequestParams(registerCheckBox.isSelected());
                    RequestInfo requestInfo = new RequestInfo("login", requestParams);
                    User user = new User(loginField.getText(), passwordField.getText());
                    MessageToServer msgToServer = new MessageToServer(user, requestInfo);

                    MessageToClient msg = client.sendMessageToServer(msgToServer);

                    if (msg.error.isEmpty()) {
                        new MainForm().setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(SignInForm.this, msg.error);
                    }

                } catch (IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(SignInForm.this, ex.getMessage());
                    logger.log(Level.SEVERE, ex.getMessage(), ex);
                }
            }
        });

        Initialize();
    }

    public void Initialize() {
        setTitle("Sign In");
        setContentPane(signInPanel);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
    }
}
