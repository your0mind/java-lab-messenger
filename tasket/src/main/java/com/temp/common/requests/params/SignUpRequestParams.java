package com.temp.common.requests.params;

public class SignUpRequestParams implements RequestParams {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public SignUpRequestParams(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
