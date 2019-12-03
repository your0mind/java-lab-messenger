package com.temp.client.messagehandlers;

import com.temp.client.Client;
import com.temp.common.updates.DialogContactUpdate;
import com.temp.common.models.Contact;

import javax.swing.*;

public class DialogContactUpdateHandler implements MessageHandler<DialogContactUpdate> {
    @Override
    public void handle(DialogContactUpdate response, Client client) {
        DefaultListModel<Contact> dialogContactsListModel = client
                .getDefaultListModels()
                .getDialogContactsListModel();

        dialogContactsListModel.addElement(response.getUpdate());
    }
}
