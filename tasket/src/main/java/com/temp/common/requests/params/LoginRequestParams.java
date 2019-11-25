package com.temp.common.requests.params;

import com.temp.model.models.User;

public class LoginRequestParams implements RequestParams {
    public LoginRequestParams(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    private User user;
}
