package com.temp.common.responses;

import com.temp.model.models.Contact;

public class CreateDialogResponse extends Response {
    private Contact dialogContact;

    public Contact getDialogContact() {
        return dialogContact;
    }

    public CreateDialogResponse(String errorMessage) {
        super(errorMessage);
    }

    public CreateDialogResponse(Contact dialogContact) {
        this.dialogContact = dialogContact;
    }
}
