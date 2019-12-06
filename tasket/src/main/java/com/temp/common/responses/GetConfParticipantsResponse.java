package com.temp.common.responses;

import com.temp.common.models.Contact;

import java.util.List;

public class GetConfParticipantsResponse extends Response {
    private List<Contact> participants;

    public List<Contact> getParticipants() {
        return participants;
    }

    public GetConfParticipantsResponse(ErrorMessage error) {
        super(error);
    }

    public GetConfParticipantsResponse(List<Contact> participants) {
        this.participants = participants;
    }
}
