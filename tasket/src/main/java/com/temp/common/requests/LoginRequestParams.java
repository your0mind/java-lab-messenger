package com.temp.common.requests;

public class LoginRequestParams implements RequestParams {
    public boolean isRegister;

    public LoginRequestParams(boolean isRegister) {
        this.isRegister = isRegister;
    }
}
