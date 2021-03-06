package com.temp.client.messagehandlers;

import com.temp.client.Client;
import com.temp.client.forms.MainForm;
import com.temp.common.models.Contact;
import com.temp.common.responses.CreateDialogResponse;

import javax.swing.*;
import java.awt.*;

public class CreateDialogResponseHandler implements MessageHandler<CreateDialogResponse> {
    @Override
    public void handle(CreateDialogResponse response, Client client) {
        if (response.hasError()) {
            Component parent = MainForm.getInstance(client);
            String message = response.getErrorMessage();
            JOptionPane.showMessageDialog(parent, message, null, JOptionPane.ERROR_MESSAGE);
        } else {
            Contact contact = response.getDialogContact();
            client.getDefaultListModels().getDialogContactsListModel().addElement(contact);
        }
    }
}
