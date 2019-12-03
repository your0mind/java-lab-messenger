package com.temp.common.responses;

import com.temp.common.models.Contact;

import java.util.List;

public class GetDialogContactsResponse extends Response {
    private List<Contact> contacts;

    public List<Contact> getContacts() {
        return contacts;
    }

    public GetDialogContactsResponse(String errorMessage) {
        super(errorMessage);
    }

    public GetDialogContactsResponse(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
