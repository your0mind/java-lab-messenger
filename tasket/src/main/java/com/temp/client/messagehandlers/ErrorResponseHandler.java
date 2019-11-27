package com.temp.client.messagehandlers;

import com.temp.client.Client;
import com.temp.common.responses.ErrorResponse;

import javax.swing.*;

public class ErrorResponseHandler implements MessageHandler<ErrorResponse> {
    @Override
    public void handle(ErrorResponse response, Client client) {
        JOptionPane.showMessageDialog(null, response.getErrorMessage());
    }
}
