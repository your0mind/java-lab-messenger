package com.temp.client.messagehandlers;

import com.temp.client.Client;
import com.temp.client.ClientDefaultListModels;
import com.temp.client.forms.MainForm;
import com.temp.common.responses.GetConferencesResponse;

import javax.swing.*;
import java.awt.*;

public class GetConferencesResponseHandler implements MessageHandler<GetConferencesResponse> {
    @Override
    public void handle(GetConferencesResponse response, Client client) {
        if (response.hasError()) {
            Component parent = MainForm.getInstance(client);
            String message = response.getErrorMessage();
            JOptionPane.showMessageDialog(parent, message, null, JOptionPane.ERROR_MESSAGE);
        } else {
            ClientDefaultListModels models = client.getDefaultListModels();
            DefaultListModel<String> model = models.getConferencesListModel();
            model.clear();
            model.addAll(response.getConferenceNames());
        }
    }
}
