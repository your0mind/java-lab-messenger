package com.temp.client.messagehandlers;

import com.temp.client.Client;
import com.temp.client.forms.MainForm;
import com.temp.client.forms.SignInForm;
import com.temp.common.responses.SignInResponse;

import javax.swing.*;

public class SignInResponseHandler implements MessageHandler<SignInResponse> {
    @Override
    public void handle(SignInResponse response, Client client) {
        if (response.hasError()) {
            JOptionPane.showMessageDialog(MainForm.getInstance(client),
                    response.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        MainForm.getInstance(client).setVisible(true);
        SignInForm.getInstance(client).dispose();
    }
}
