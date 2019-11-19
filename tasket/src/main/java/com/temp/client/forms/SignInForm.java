package com.temp.client.forms;

import com.temp.client.Client;
import com.temp.common.requests.LoginRequest;
import com.temp.common.requests.RegisterRequest;
import com.temp.common.responses.ErrorResponse;
import com.temp.common.responses.LoginResponse;
import com.temp.common.responses.Response;
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
    private final static Logger logger = Logger.getLogger(SignInForm.class.getSimpleName());

    public SignInForm() {
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = Client.getInstance();

                try {
                    client.connectToServer("localhost", 4004);
                    logger.log(Level.INFO, "Connection to server successfully created");

                    User user = new User(loginField.getText(), passwordField.getText());

                    if (registerCheckBox.isSelected()) {
                        Response response = client.sendRequestToServer(new RegisterRequest(user, null));

                        if (response instanceof ErrorResponse) {
                            String errorMessage = ((ErrorResponse) response).getErrorMessage();
                            JOptionPane.showMessageDialog(SignInForm.this, errorMessage);
                            return;
                        }
                    }

                    Response response = client.sendRequestToServer(new LoginRequest(user, null));

                    if (response instanceof LoginResponse) {
                        user.id = ((LoginResponse) response).getClientId();
                        client.getData().setUser(user);
                        new MainForm().setVisible(true);
                        dispose();
                    } else if (response instanceof ErrorResponse){
                        String errorMessage = ((ErrorResponse) response).getErrorMessage();
                        JOptionPane.showMessageDialog(SignInForm.this, errorMessage);
                    } else {
                        JOptionPane.showMessageDialog(SignInForm.this, "Something went wrong");
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
