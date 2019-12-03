package com.temp.client.messagehandlers;

import com.temp.client.Client;
import com.temp.client.forms.MainForm;
import com.temp.common.responses.GetDialogContactsResponse;
import com.temp.common.models.Contact;

import javax.swing.*;

public class GetDialogContactsResponseHandler implements MessageHandler<GetDialogContactsResponse> {
    @Override
    public void handle(GetDialogContactsResponse response, Client client) {
        if (response.hasError()) {
            JOptionPane.showMessageDialog(MainForm.getInstance(client),
                    response.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultListModel<Contact> dialogContactsModel = client
                .getDefaultListModels()
                .getDialogContactsListModel();

        dialogContactsModel.clear();
        dialogContactsModel.addAll(response.getContacts());
    }
}
