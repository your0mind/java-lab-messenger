package com.temp.common.requests.params;

import com.temp.model.models.Contact;

public class CreateDialogRequestParams implements RequestParams {
    private Contact contact;

    public Contact getContact() {
        return contact;
    }

    public CreateDialogRequestParams(Contact contact) {
        this.contact = contact;
    }
}
