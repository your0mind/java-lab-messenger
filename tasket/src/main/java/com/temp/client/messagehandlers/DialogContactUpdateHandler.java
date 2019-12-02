package com.temp.client.messagehandlers;

import com.temp.client.Client;
import com.temp.common.responses.GetContactsResponse;
import com.temp.common.updates.DialogContactUpdate;
import com.temp.model.models.Contact;

import javax.swing.*;
import java.util.List;

public class DialogContactUpdateHandler implements MessageHandler<DialogContactUpdate> {
    @Override
    public void handle(DialogContactUpdate response, Client client) {
        DefaultListModel<Contact> dialogContactsListModel = client
                .getDefaultListModels()
                .getDialogContactsListModel();

        dialogContactsListModel.addElement(response.getUpdate());
    }
}