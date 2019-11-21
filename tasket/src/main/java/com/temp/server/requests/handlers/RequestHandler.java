package com.temp.server.requests.handlers;

import com.temp.common.requests.Request;
import com.temp.common.responses.Response;

public interface RequestHandler<T extends Request> {
    Response handle(T request);
}
