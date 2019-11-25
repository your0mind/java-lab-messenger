package com.temp.common.responses;

import com.temp.model.models.Dialog;

import java.util.List;

public class GetDialogsResponse implements Response {
    private List<Dialog> dialogs;

    public GetDialogsResponse(List<Dialog> dialogs) {
        this.dialogs = dialogs;
    }

    public List<Dialog> getDialogs() {
        return dialogs;
    }

    public void setDialogs(List<Dialog> dialogs) {
        this.dialogs = dialogs;
    }
}
