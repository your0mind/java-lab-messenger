package com.temp.client.messagehandlers;

import com.temp.client.Client;
import com.temp.common.responses.GetContactsResponse;
import com.temp.common.responses.GetDialogContactsResponse;

import javax.swing.*;
import java.util.List;

public class GetContactsResponseHandler implements MessageHandler<GetContactsResponse> {
    @Override
    public void handle(GetContactsResponse response, Client client) {
        DefaultListModel<String> contactsModel = client
                .getDefaultListModels()
                .getContactsListModel();

        List<String> contacts = response.getContacts();

        for (String contact: contacts) {
            contactsModel.addElement(contact);
        }
    }
}
