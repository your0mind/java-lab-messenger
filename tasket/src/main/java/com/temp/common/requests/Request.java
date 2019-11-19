package com.temp.common.requests;

import com.temp.common.requests.params.RequestParams;
import com.temp.model.models.User;

public abstract class Request<T extends RequestParams> {
    public Request(User requester, T params) {
        this.requester = requester;
        this.params = params;
    }

    public User getRequester() {
        return requester;
    }

    public T getParams() {
        return params;
    }

    private User requester;
    private T params;
}
