package com.temp.client.messagehandlers;

import com.temp.client.Client;
import com.temp.common.responses.GetDialogsResponse;
import com.temp.model.models.Dialog;

import javax.swing.*;
import java.util.List;

public class GetDialogsResponseHandler implements MessageHandler<GetDialogsResponse> {
    @Override
    public void handle(GetDialogsResponse response) {
        Client client = Client.getInstance();

        DefaultListModel<Dialog> dialogModel = client.getMainForm().getDialogListModel();
        List<Dialog> dialogs = response.getDialogs();

        for (Dialog dialog: dialogs) {
            dialogModel.addElement(dialog);
        }
    }
}
