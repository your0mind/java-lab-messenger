package com.temp.common.requests;

import com.temp.common.requests.params.SignInRequestParams;

public class SignInRequest extends Request<SignInRequestParams> {
    public SignInRequest(SignInRequestParams params) {
        super(params);
    }
}
