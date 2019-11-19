package com.temp.common.responses;

public class LoginResponse implements Response {
    public int getClientId() {
        return clientId;
    }

    private int clientId;

    public LoginResponse(int clientId) {
        this.clientId = clientId;
    }
}
