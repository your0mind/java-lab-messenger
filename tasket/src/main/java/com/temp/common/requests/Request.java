package com.temp.common.requests;

import com.temp.common.requests.params.RequestParams;

import java.io.Serializable;

public abstract class Request<T extends RequestParams> implements Serializable {
    public Request(T params) {
        this.params = params;
    }

    public T getParams() {
        return params;
    }

    private T params;
}
