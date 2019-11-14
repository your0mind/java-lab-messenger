package com.temp.server.exceptions;

public class UnknownRequestException extends Exception {
    public UnknownRequestException(String message) {
        super(message);
    }
}
