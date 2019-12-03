package com.temp.client.messagehandlers;

import com.temp.client.Client;
import com.temp.client.forms.MainForm;
import com.temp.common.responses.SendDialogMessageResponse;

import javax.swing.*;

public class SendDialogMessageResponseHandler implements MessageHandler<SendDialogMessageResponse> {
    @Override
    public void handle(SendDialogMessageResponse response, Client client) {
        if (response.hasError()) {
            JOptionPane.showMessageDialog(MainForm.getInstance(client),
                    response.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        client.getDefaultListModels().getDialogMessagesListModel()
                .addElement(response.getSentMessage());
    }
}
