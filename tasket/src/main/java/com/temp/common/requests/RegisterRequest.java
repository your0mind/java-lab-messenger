package com.temp.common.requests;

import com.temp.common.requests.params.RequestParams;
import com.temp.model.models.User;

public class RegisterRequest extends Request {
    public RegisterRequest(User requester, RequestParams params) {
        super(requester, params);
    }
}
