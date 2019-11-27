package com.temp.common.responses;

public class CreateDialogResponse implements Response {
    private String dialogContact;

    public String getDialogContact() {
        return dialogContact;
    }

    public CreateDialogResponse(String dialogContact) {
        this.dialogContact = dialogContact;
    }
}
