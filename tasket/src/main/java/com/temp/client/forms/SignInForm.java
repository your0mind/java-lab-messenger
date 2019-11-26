package com.temp.client.forms;

import com.temp.client.Client;
import com.temp.client.ClientThread;
import com.temp.common.requests.LoginRequest;
import com.temp.common.requests.RegisterRequest;
import com.temp.common.requests.params.LoginRequestParams;
import com.temp.common.requests.params.RegisterRequestParams;
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
                ClientThread clientThread = Client.getInstance().getClientThread();

                try {
                    User user = new User(loginField.getText(), passwordField.getText());

                    if (registerCheckBox.isSelected()) {
                        RegisterRequest request = new RegisterRequest(new RegisterRequestParams(user));
                        clientThread.sendRequestToServer(request);
                    }

                    LoginRequest request = new LoginRequest(new LoginRequestParams(user));
                    clientThread.sendRequestToServer(request);

                    SignInForm.this.setEnabled(false);

                } catch (IOException ex) {
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
