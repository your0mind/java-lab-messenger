package com.temp.server.requesthandlers;

import com.temp.common.requests.GetDialogContactsRequest;
import com.temp.common.requests.LoginRequest;
import com.temp.common.requests.RegisterRequest;
import com.temp.common.requests.Request;
import com.temp.server.exceptions.*;

public class RequestHandlerBuilder {
    public static RequestHandler build(Request request) throws UnknownRequestException {
        if      (request instanceof RegisterRequest)    return new RegisterRequestHandler();
        else if (request instanceof LoginRequest)       return new LoginRequestHandler();
        else if (request instanceof GetDialogContactsRequest)  return new GetDialogContactsRequestHandler();
        else                                            throw new UnknownRequestException();
    }
}
