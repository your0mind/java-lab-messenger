package com.temp.common.requests;

import com.temp.common.requests.params.SignUpRequestParams;

public class SignUpRequest extends Request<SignUpRequestParams> {
    public SignUpRequest(SignUpRequestParams params) {
        super(params);
    }
}
