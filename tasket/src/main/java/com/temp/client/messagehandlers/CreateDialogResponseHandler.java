package com.temp.client.messagehandlers;

import com.temp.client.Client;
import com.temp.client.forms.MainForm;
import com.temp.common.responses.CreateDialogResponse;
import com.temp.model.models.Contact;

import javax.swing.*;

public class CreateDialogResponseHandler implements MessageHandler<CreateDialogResponse> {
    @Override
    public void handle(CreateDialogResponse response, Client client) {
        if (response.hasError()) {
            JOptionPane.showMessageDialog(MainForm.getInstance(client),
                    response.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultListModel<Contact> dialogContactsListModel = client
                .getDefaultListModels()
                .getDialogContactsListModel();

        dialogContactsListModel.addElement(response.getDialogContact());
    }
}
