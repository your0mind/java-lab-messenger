package com.temp.client.messagehandlers;

import com.temp.client.Client;
import com.temp.client.ClientDefaultListModels;
import com.temp.client.forms.MainForm;
import com.temp.common.models.ChatMessage;
import com.temp.common.responses.GetDialogMessagesResponse;

import javax.swing.*;
import java.awt.*;

public class GetDialogMessagesResponseHandler implements MessageHandler<GetDialogMessagesResponse> {
    @Override
    public void handle(GetDialogMessagesResponse response, Client client) {
        if (response.hasError()) {
            Component parent = MainForm.getInstance(client);
            String message = response.getErrorMessage();
            JOptionPane.showMessageDialog(parent, message, null, JOptionPane.ERROR_MESSAGE);
        } else {
            ClientDefaultListModels models = client.getDefaultListModels();
            DefaultListModel<ChatMessage> model = models.getDialogMessagesListModel();
            model.clear();
            model.addAll(response.getMessages());
        }
    }
}
