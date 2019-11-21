package com.temp.common.requests;

import com.temp.common.requests.params.RequestParams;
import com.temp.model.models.User;

import java.io.Serializable;

public abstract class Request<T extends RequestParams> implements Serializable {
    public Request(User requester, T params) {
        this.requester = requester;
        this.params = params;
    }

    public Request(User requester) {
        this.requester = requester;
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
