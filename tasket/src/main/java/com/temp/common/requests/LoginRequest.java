package com.temp.common.requests;

import com.temp.common.requests.params.LoginRequestParams;
import com.temp.model.models.User;

public class LoginRequest extends Request {
    public LoginRequest(User requester, LoginRequestParams params) {
        super(requester, params);
    }
}
