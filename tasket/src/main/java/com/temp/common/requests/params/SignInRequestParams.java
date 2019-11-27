package com.temp.common.requests.params;

public class SignInRequestParams implements RequestParams {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public SignInRequestParams(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
