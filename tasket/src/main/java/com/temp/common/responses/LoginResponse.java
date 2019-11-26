package com.temp.common.responses;

import com.temp.model.models.User;

public class LoginResponse implements Response {
    private User client;

    public LoginResponse(User client) {
        this.client = client;
    }

    public User getClient() {
        return client;
    }
}
