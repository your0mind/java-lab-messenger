package com.temp.client.messagehandlers;

import com.temp.client.Client;
import com.temp.client.forms.SignInForm;
import com.temp.common.responses.SignUpResponse;

public class SignUpResponseHandler implements MessageHandler<SignUpResponse> {
    @Override
    public void handle(SignUpResponse response, Client client) {
        SignInForm.getInstance(client).setEnabled(true);
    }
}
