package com.temp.common.requests.params;

public class CreateDialogRequestParams implements RequestParams {
    private String contactUsername;

    public String getContactUsername() {
        return contactUsername;
    }

    public CreateDialogRequestParams(String contactUsername) {
        this.contactUsername = contactUsername;
    }
}
