package com.temp.server.requests;

import com.temp.common.requests.LoginRequestParams;
import com.temp.common.requests.RequestParams;
import com.temp.model.models.User;

public class GetMessagesRequest implements Request {
    @Override
    public Class getParamsClass() {
        return LoginRequestParams.class;
    }

    @Override
    public void handle(User requester, RequestParams params) {

    }
}
