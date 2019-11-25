package com.temp.client.forms;

import com.temp.client.Client;
import com.temp.common.requests.LoginRequest;
import com.temp.common.requests.RegisterRequest;
import com.temp.common.requests.params.LoginRequestParams;
import com.temp.common.requests.params.RegisterRequestParams;
import com.temp.common.responses.ErrorResponse;
import com.temp.common.responses.LoginResponse;
import com.temp.common.responses.Response;
import com.temp.model.models.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
                    User user = new User(loginField.getText(), passwordField.getText());

                    if (registerCheckBox.isSelected()) {
                        RegisterRequest request = new RegisterRequest(new RegisterRequestParams(user));
                        Response response = client.sendRequestToServer(request);

                        if (response instanceof ErrorResponse) {
                            throw new Exception(((ErrorResponse) response).getErrorMessage());
                        }
                    }

                    LoginRequest request = new LoginRequest(new LoginRequestParams(user));
                    Response response = client.sendRequestToServer(request);

                    if (response instanceof LoginResponse) {
                        client.setUsername(user.getUsername());

                        setVisible(false);
                        new MainForm().setVisible(true);

                    } else if (response instanceof ErrorResponse){
                        throw new Exception(((ErrorResponse) response).getErrorMessage());

                    } else {
                        throw new Exception("Something went wrong");
                    }

                } catch (Exception ex) {
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
