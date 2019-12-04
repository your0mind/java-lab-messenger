package com.temp.client.messagehandlers;

import com.temp.client.Client;
import com.temp.client.forms.MainForm;
import com.temp.common.responses.GetContactsResponse;
import com.temp.common.models.Contact;

import javax.swing.*;
import java.awt.*;

public class GetContactsResponseHandler implements MessageHandler<GetContactsResponse> {
    @Override
    public void handle(GetContactsResponse response, Client client) {
        if (response.hasError()) {
            Component parent = MainForm.getInstance(client);
            String message = response.getErrorMessage();
            JOptionPane.showMessageDialog(parent, message, null, JOptionPane.ERROR_MESSAGE);
        } else {
            DefaultListModel<Contact> model = client.getDefaultListModels().getContactsListModel();
            model.clear();
            model.addAll(response.getContacts());
        }
    }
}
