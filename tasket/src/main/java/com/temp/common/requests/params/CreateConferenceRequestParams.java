package com.temp.common.requests.params;

import com.temp.common.models.Contact;

import java.util.List;

public class CreateConferenceRequestParams implements RequestParams {
    private List<Contact> participants;
    private String name;

    public List<Contact> getParticipants() {
        return participants;
    }

    public String getName() {
        return name;
    }

    public CreateConferenceRequestParams(List<Contact> participants, String name) {
        this.participants = participants;
        this.name = name;
    }
}
