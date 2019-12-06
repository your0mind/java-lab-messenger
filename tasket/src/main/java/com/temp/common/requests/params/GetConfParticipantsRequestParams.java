package com.temp.common.requests.params;

public class GetConfParticipantsRequestParams implements RequestParams {
    private String conferenceName;

    public String getConferenceName() {
        return conferenceName;
    }

    public GetConfParticipantsRequestParams(String conferenceName) {
        this.conferenceName = conferenceName;
    }
}
