package com.temp.common.requests.params;

public class GetDialogsRequestParams implements RequestParams {
    private boolean listenUpdates;

    public GetDialogsRequestParams(boolean listenUpdates) {
        this.listenUpdates = listenUpdates;
    }

    public boolean isListenUpdates() {
        return listenUpdates;
    }
}
