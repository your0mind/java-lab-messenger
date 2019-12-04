package com.temp.common.responses;

public class CreateConferenceResponse extends Response {
    private String conferenceName;

    public String getConferenceName() {
        return conferenceName;
    }

    public CreateConferenceResponse(ErrorMessage error) {
        super(error);
    }

    public CreateConferenceResponse(String conferenceName) {
        this.conferenceName = conferenceName;
    }
}
