package com.temp.common.requests;

import com.temp.model.models.User;

public class LoginRequestParams implements RequestParams {
    public User user;
    public boolean isRegister;

    public LoginRequestParams(User user, boolean isRegister) {
        this.user = user;
        this.isRegister = isRegister;
    }
}
