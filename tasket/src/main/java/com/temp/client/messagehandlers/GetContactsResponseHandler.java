package com.temp.client.messagehandlers;

import com.temp.client.Client;
import com.temp.client.forms.MainForm;
import com.temp.common.responses.GetContactsResponse;
import com.temp.common.models.Contact;

import javax.swing.*;

public class GetContactsResponseHandler implements MessageHandler<GetContactsResponse> {
    @Override
    public void handle(GetContactsResponse response, Client client) {
        if (response.hasError()) {
            JOptionPane.showMessageDialog(MainForm.getInstance(client),
                    response.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultListModel<Contact> contactsModel = client
                .getDefaultListModels()
                .getContactsListModel();

        contactsModel.clear();
        contactsModel.addAll(response.getContacts());
    }
}
