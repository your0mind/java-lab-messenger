package com.temp.common.requests.params;

import com.temp.common.models.Contact;

public class SendDialogMessageRequestParams implements RequestParams {
    private String Text;
    private Contact dialogContact;

    public String getText() {
        return Text;
    }

    public Contact getDialogContact() {
        return dialogContact;
    }

    public SendDialogMessageRequestParams(String text, Contact dialogContact) {
        Text = text;
        this.dialogContact = dialogContact;
    }
}
