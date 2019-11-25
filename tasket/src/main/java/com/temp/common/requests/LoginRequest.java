package com.temp.common.requests;

import com.temp.common.requests.params.LoginRequestParams;

public class LoginRequest extends Request<LoginRequestParams> {
    public LoginRequest(LoginRequestParams params) {
        super(params);
    }
}
