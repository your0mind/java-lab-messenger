package com.temp.common.responses;

import java.util.List;

public class GetDialogContactsResponse implements Response {
    private List<String> contacts;

    public List<String> getContacts() {
        return contacts;
    }

    public void setContacts(List<String> contacts) {
        this.contacts = contacts;
    }

    public GetDialogContactsResponse(List<String> contacts) {
        this.contacts = contacts;
    }
}
