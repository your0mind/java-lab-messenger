package com.temp.client.messagehandlers;

import com.temp.client.Client;
import com.temp.client.ClientDefaultListModels;
import com.temp.client.forms.MainForm;
import com.temp.common.responses.GetDialogContactsResponse;
import com.temp.common.models.Contact;

import javax.swing.*;
import java.awt.*;

public class GetDialogContactsResponseHandler
        implements MessageHandler<GetDialogContactsResponse> {
    @Override
    public void handle(GetDialogContactsResponse response, Client client) {
        if (response.hasError()) {
            Component parent = MainForm.getInstance(client);
            String message = response.getErrorMessage();
            JOptionPane.showMessageDialog(parent, message, null, JOptionPane.ERROR_MESSAGE);
        } else {
            ClientDefaultListModels models = client.getDefaultListModels();
            DefaultListModel<Contact> model = models.getDialogContactsListModel();
            model.clear();
            model.addAll(response.getContacts());
        }
    }
}
