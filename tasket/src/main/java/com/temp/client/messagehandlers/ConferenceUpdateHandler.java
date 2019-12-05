package com.temp.client.messagehandlers;

import com.temp.client.Client;
import com.temp.client.ClientDefaultListModels;
import com.temp.common.updates.ConferenceUpdate;

public class ConferenceUpdateHandler implements MessageHandler<ConferenceUpdate> {
    @Override
    public void handle(ConferenceUpdate message, Client client) {
        ClientDefaultListModels models = client.getDefaultListModels();
        models.getConferencesListModel().addElement(message.getUpdate());
    }
}
