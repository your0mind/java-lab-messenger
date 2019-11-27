package com.temp.common.requests.params;

public class GetDialogContactsRequestParams implements RequestParams {
    private boolean listenUpdates;

    public GetDialogContactsRequestParams(boolean listenUpdates) {
        this.listenUpdates = listenUpdates;
    }

    public boolean isListenUpdates() {
        return listenUpdates;
    }
}
