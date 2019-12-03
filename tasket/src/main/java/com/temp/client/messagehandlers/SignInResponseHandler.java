package com.temp.client.messagehandlers;

import com.temp.client.Client;
import com.temp.client.forms.MainForm;
import com.temp.client.forms.SignInForm;
import com.temp.common.responses.SignInResponse;

import javax.swing.*;

public class SignInResponseHandler implements MessageHandler<SignInResponse> {
    @Override
    public void handle(SignInResponse response, Client client) {
        SignInForm signInForm = SignInForm.getInstance(client);
        signInForm.setEnabled(true);

        if (response.hasError()) {
            JOptionPane.showMessageDialog(signInForm,
                    response.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        MainForm.getInstance(client).setVisible(true);
        signInForm.dispose();
    }
}
