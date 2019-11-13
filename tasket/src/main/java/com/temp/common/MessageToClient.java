package com.temp.common;

public class MessageToClient {
    public String text;
    public String error;

    public MessageToClient(String text, String error) {
        this.text = text;
        this.error = error;
    }
}
