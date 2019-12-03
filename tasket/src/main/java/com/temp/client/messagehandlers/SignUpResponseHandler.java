package com.temp.client.messagehandlers;

import com.temp.client.Client;
import com.temp.client.forms.MainForm;
import com.temp.client.forms.SignInForm;
import com.temp.common.responses.SignUpResponse;

import javax.swing.*;

public class SignUpResponseHandler implements MessageHandler<SignUpResponse> {
    @Override
    public void handle(SignUpResponse response, Client client) {
        SignInForm signInForm = SignInForm.getInstance(client);
        signInForm.setEnabled(true);

        if (response.hasError()) {
            JOptionPane.showMessageDialog(signInForm,
                    response.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(signInForm, "Successful registration");
    }
}
