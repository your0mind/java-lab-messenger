package com.temp.common.requests.params;

public class GetConferencesRequestParams implements RequestParams {
    private boolean listenUpdates;

    public GetConferencesRequestParams(boolean listenUpdates) {
        this.listenUpdates = listenUpdates;
    }

    public boolean isListenUpdates() {
        return listenUpdates;
    }
}
