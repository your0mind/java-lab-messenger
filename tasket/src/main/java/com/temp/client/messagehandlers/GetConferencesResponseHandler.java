package com.temp.client.messagehandlers;

import com.temp.client.Client;
import com.temp.client.forms.MainForm;
import com.temp.common.responses.GetConferencesResponse;

import javax.swing.*;

public class GetConferencesResponseHandler implements MessageHandler<GetConferencesResponse> {
    @Override
    public void handle(GetConferencesResponse response, Client client) {
        if (response.hasError()) {
            JOptionPane.showMessageDialog(MainForm.getInstance(client),
                    response.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultListModel<String> model = client.getDefaultListModels().getConferencesListModel();

        model.clear();
        model.addAll(response.getConferenceNames());
    }
}
