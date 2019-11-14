package com.temp.common;

import com.temp.common.requests.RequestInfo;
import com.temp.model.models.User;

public class MessageToServer {
    public User user;
    public RequestInfo requestInfo;

    public MessageToServer(User user, RequestInfo requestInfo) {
        this.user = user;
        this.requestInfo = requestInfo;
    }
}
