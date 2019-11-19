package com.temp.common.responses;

public class ErrorResponse implements Response {
    public ErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    private String errorMessage;
}
