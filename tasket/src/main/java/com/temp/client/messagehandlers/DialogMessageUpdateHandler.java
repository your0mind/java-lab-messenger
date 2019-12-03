package com.temp.client.messagehandlers;

import com.temp.client.Client;
import com.temp.common.models.ChatMessage;
import com.temp.common.updates.DialogMessageUpdate;

import javax.swing.*;

public class DialogMessageUpdateHandler implements MessageHandler<DialogMessageUpdate> {
    @Override
    public void handle(DialogMessageUpdate response, Client client) {
        DefaultListModel<ChatMessage> dialogMessagesListModel = client
                .getDefaultListModels()
                .getDialogMessagesListModel();

        dialogMessagesListModel.addElement(response.getUpdate());
    }
}
