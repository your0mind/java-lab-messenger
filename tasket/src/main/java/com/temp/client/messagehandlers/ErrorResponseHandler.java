package com.temp.client.messagehandlers;

import com.temp.common.responses.ErrorResponse;

import javax.swing.*;

public class ErrorResponseHandler implements MessageHandler<ErrorResponse> {
    @Override
    public void handle(ErrorResponse response) {
        JOptionPane.showMessageDialog(null, response.getErrorMessage());
    }
}
