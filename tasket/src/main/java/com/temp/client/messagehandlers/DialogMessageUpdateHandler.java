package com.temp.client.messagehandlers;

import com.temp.client.Client;
import com.temp.client.ClientDefaultListModels;
import com.temp.common.updates.DialogMessageUpdate;

public class DialogMessageUpdateHandler implements MessageHandler<DialogMessageUpdate> {
    @Override
    public void handle(DialogMessageUpdate response, Client client) {
        ClientDefaultListModels models = client.getDefaultListModels();
        models.getDialogMessagesListModel().addElement(response.getUpdate());
    }
}
