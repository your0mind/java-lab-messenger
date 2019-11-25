package com.temp.server.requests;

import com.temp.common.requests.LoginRequest;
import com.temp.common.requests.RegisterRequest;
import com.temp.common.requests.Request;
import com.temp.server.exceptions.*;
import com.temp.server.requests.handlers.LoginRequestHandler;
import com.temp.server.requests.handlers.RegisterRequestHandler;
import com.temp.server.requests.handlers.RequestHandler;

public class RequestHandlerBuilder {
    public static RequestHandler build(Request request) throws UnknownRequestException {
        if      (request instanceof RegisterRequest)    return new RegisterRequestHandler();
        else if (request instanceof LoginRequest)       return new LoginRequestHandler();
        else                                            return null;
    }
}
