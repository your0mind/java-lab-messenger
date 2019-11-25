package com.temp.common.requests.params;

import com.temp.model.models.User;

public class RegisterRequestParams implements RequestParams {
    public RegisterRequestParams(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    private User user;
}
