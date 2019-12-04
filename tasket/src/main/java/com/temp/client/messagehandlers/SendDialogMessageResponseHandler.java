package com.temp.client.messagehandlers;

import com.temp.client.Client;
import com.temp.client.ClientDefaultListModels;
import com.temp.client.forms.MainForm;
import com.temp.common.responses.SendDialogMessageResponse;

import javax.swing.*;
import java.awt.*;

public class SendDialogMessageResponseHandler implements MessageHandler<SendDialogMessageResponse> {
    @Override
    public void handle(SendDialogMessageResponse response, Client client) {
        if (response.hasError()) {
            Component parent = MainForm.getInstance(client);
            String message = response.getErrorMessage();
            JOptionPane.showMessageDialog(parent, message, null, JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            ClientDefaultListModels models = client.getDefaultListModels();
            models.getDialogMessagesListModel().addElement(response.getSentMessage());
        }
    }
}
