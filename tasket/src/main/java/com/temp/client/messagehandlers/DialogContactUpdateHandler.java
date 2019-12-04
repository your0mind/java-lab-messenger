package com.temp.client.messagehandlers;

import com.temp.client.Client;
import com.temp.client.ClientDefaultListModels;
import com.temp.common.updates.DialogContactUpdate;

public class DialogContactUpdateHandler implements MessageHandler<DialogContactUpdate> {
    @Override
    public void handle(DialogContactUpdate response, Client client) {
        ClientDefaultListModels models = client.getDefaultListModels();
        models.getDialogContactsListModel().addElement(response.getUpdate());
    }
}
