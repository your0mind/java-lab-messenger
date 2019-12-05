package com.temp.common.responses;

import java.io.Serializable;

public class ErrorMessage implements Serializable {
    private String message;

    public String getMessage() {
        return message;
    }

    public ErrorMessage(String message) {
        this.message = message;
    }
}
