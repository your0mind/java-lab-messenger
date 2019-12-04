package com.temp.client.messagehandlers;

import com.temp.client.Client;
import com.temp.client.forms.SignInForm;
import com.temp.common.responses.SignUpResponse;

import javax.swing.*;

public class SignUpResponseHandler implements MessageHandler<SignUpResponse> {
    @Override
    public void handle(SignUpResponse response, Client client) {
        SignInForm signInForm = SignInForm.getInstance(client);
        signInForm.setEnabled(true);

        if (response.hasError()) {
            String message = response.getErrorMessage();
            JOptionPane.showMessageDialog(signInForm, message, null, JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(signInForm, "Successful registration");
        }
    }
}
