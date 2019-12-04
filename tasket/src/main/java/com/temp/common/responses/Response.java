package com.temp.common.responses;


import com.temp.common.Message;

public abstract class Response implements Message {
    private ErrorMessage error = null;

    public String getErrorMessage() {
        return error.getMessage();
    }

    public boolean hasError() {
        return error != null;
    }

    public Response() {
    }

    public Response(ErrorMessage error) {
        this.error = error;
    }
}
