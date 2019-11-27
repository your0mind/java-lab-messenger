package com.temp.client.messagehandlers;

import com.temp.client.Client;
import com.temp.client.forms.MainForm;
import com.temp.client.forms.SignInForm;
import com.temp.common.responses.SignInResponse;

public class SignInResponseHandler implements MessageHandler<SignInResponse> {
    @Override
    public void handle(SignInResponse response, Client client) {
        MainForm.getInstance(client).setVisible(true);
        SignInForm.getInstance(client).dispose();
    }
}
