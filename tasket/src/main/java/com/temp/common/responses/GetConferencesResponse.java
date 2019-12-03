package com.temp.common.responses;

import java.util.List;

public class GetConferencesResponse extends Response {
    private List<String> conferenceNames;

    public List<String> getConferenceNames() {
        return conferenceNames;
    }

    public GetConferencesResponse(String errorMessage) {
        super(errorMessage);
    }

    public GetConferencesResponse(List<String> conferenceNames) {
        this.conferenceNames = conferenceNames;
    }
}
