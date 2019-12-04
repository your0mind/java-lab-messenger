package com.temp.common.responses;

import com.temp.common.models.Contact;

import java.util.List;

public class GetContactsResponse extends Response {
    private List<Contact> contacts;

    public List<Contact> getContacts() {
        return contacts;
    }

    public GetContactsResponse(ErrorMessage error) {
        super(error);
    }

    public GetContactsResponse(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
