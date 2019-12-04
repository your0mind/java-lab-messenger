package com.temp.client.messagehandlers;

import com.temp.client.Client;
import com.temp.client.forms.MainForm;
import com.temp.common.responses.CreateConferenceResponse;

import javax.swing.*;
import java.awt.*;

public class CreateConferenceResponseHandler implements MessageHandler<CreateConferenceResponse> {
    @Override
    public void handle(CreateConferenceResponse response, Client client) {
        if (response.hasError()) {
            Component parent = MainForm.getInstance(client);
            String message = response.getErrorMessage();
            JOptionPane.showMessageDialog(parent, message, null, JOptionPane.ERROR_MESSAGE);
        } else {
            String conferenceName = response.getConferenceName();
            client.getDefaultListModels().getConferencesListModel().addElement(conferenceName);
        }
    }
}
