package com.temp.common.requests;

public class RequestInfo {
    public String type;
    public RequestParams params;

    public RequestInfo(String type, RequestParams params) {
        this.type = type;
        this.params = params;
    }
}
