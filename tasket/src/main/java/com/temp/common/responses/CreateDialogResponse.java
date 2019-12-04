package com.temp.common.responses;

import com.temp.common.models.Contact;

public class CreateDialogResponse extends Response {
    private Contact dialogContact;

    public Contact getDialogContact() {
        return dialogContact;
    }

    public CreateDialogResponse(ErrorMessage error) {
        super(error);
    }

    public CreateDialogResponse(Contact dialogContact) {
        this.dialogContact = dialogContact;
    }
}
