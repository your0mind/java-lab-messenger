package com.temp.common.responses;

import java.util.List;

public class GetConferencesResponse extends Response {
    private List<String> conferenceNames;

    public List<String> getConferenceNames() {
        return conferenceNames;
    }

    public GetConferencesResponse(ErrorMessage error) {
        super(error);
    }

    public GetConferencesResponse(List<String> conferenceNames) {
        this.conferenceNames = conferenceNames;
    }
}
