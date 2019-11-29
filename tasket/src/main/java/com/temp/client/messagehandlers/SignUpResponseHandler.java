package com.temp.client.messagehandlers;

import com.temp.client.Client;
import com.temp.client.forms.SignInForm;
import com.temp.common.responses.SignUpResponse;

import javax.swing.*;

public class SignUpResponseHandler implements MessageHandler<SignUpResponse> {
    @Override
    public void handle(SignUpResponse response, Client client) {
        SignInForm.getInstance(client).setEnabled(true);
        JOptionPane.showMessageDialog(SignInForm.getInstance(client), "Successful registration");
    }
}
