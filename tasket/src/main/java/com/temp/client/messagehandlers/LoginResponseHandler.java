package com.temp.client.messagehandlers;

import com.temp.client.Client;
import com.temp.client.forms.MainForm;
import com.temp.common.responses.LoginResponse;

public class LoginResponseHandler implements MessageHandler<LoginResponse> {
    @Override
    public void handle(LoginResponse response) {
        Client client = Client.getInstance();

        client.setUsername(response.getClient().getUsername());
        client.setMainForm(new MainForm());
        client.getMainForm().setVisible(true);
        client.getSignInForm().setVisible(false);
    }
}
