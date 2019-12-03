package com.temp.common.responses;

import com.temp.common.models.Contact;

import java.util.List;

public class GetContactsResponse extends Response {
    private List<Contact> contacts;

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public GetContactsResponse(String errorMessage) {
        super(errorMessage);
    }

    public GetContactsResponse(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
