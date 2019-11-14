package com.temp.server.requests;

import com.temp.common.MessageToClient;
import com.temp.common.requests.RequestParams;
import com.temp.model.models.User;

public interface Request {
    Class getParamsClass();
    MessageToClient createResponse(User requester, RequestParams params);
}
