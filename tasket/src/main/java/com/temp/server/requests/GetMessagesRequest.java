package com.temp.server.requests;

import com.temp.common.MessageToClient;
import com.temp.common.requests.LoginRequestParams;
import com.temp.common.requests.RequestParams;
import com.temp.model.models.User;

public class GetMessagesRequest implements Request {
    @Override
    public Class getParamsClass() {
        return LoginRequestParams.class;
    }

    @Override
    public MessageToClient createResponse(User requester, RequestParams params) {
        return null;
    }
}
