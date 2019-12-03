package com.temp.common.requests.params;

import com.temp.common.models.Contact;

public class GetDialogMessagesRequestParams implements RequestParams {
    private Contact dialogContact;
    private boolean listenUpdates;

    public Contact getDialogContact() {
        return dialogContact;
    }

    public boolean isListenUpdates() {
        return listenUpdates;
    }

    public GetDialogMessagesRequestParams(Contact dialogContact, boolean listenUpdates) {
        this.dialogContact = dialogContact;
        this.listenUpdates = listenUpdates;
    }
}
