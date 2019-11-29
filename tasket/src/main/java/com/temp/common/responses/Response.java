package com.temp.common.responses;


import com.temp.common.Message;

public abstract class Response implements Message {
    private String errorMessage = null;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean hasError() {
        return errorMessage != null;
    }

    public Response() {
    }

    public Response(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
