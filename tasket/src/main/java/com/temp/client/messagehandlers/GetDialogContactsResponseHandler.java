package com.temp.client.messagehandlers;

import com.temp.client.Client;
import com.temp.common.responses.GetDialogContactsResponse;

import javax.swing.*;
import java.util.List;

public class GetDialogContactsResponseHandler implements MessageHandler<GetDialogContactsResponse> {
    @Override
    public void handle(GetDialogContactsResponse response) {
        Client client = Client.getInstance();

        DefaultListModel<String> dialogContactsModel = client.getMainForm().getDialogContactsListModel();
        List<String> contacts = response.getContacts();

        for (String contact: contacts) {
            dialogContactsModel.addElement(contact);
        }
    }
}
