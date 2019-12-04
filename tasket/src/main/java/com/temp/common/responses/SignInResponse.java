package com.temp.common.responses;

public class SignInResponse extends Response {
    public SignInResponse() {
    }

    public SignInResponse(ErrorMessage error) {
        super(error);
    }
}
