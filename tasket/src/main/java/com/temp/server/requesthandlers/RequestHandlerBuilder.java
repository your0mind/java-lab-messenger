package com.temp.server.requesthandlers;

import com.temp.common.requests.*;
import com.temp.server.exceptions.*;

public class RequestHandlerBuilder {
    public static RequestHandler build(Request request) throws UnknownRequestException {
        if      (request instanceof SignUpRequest)              return new SignUpRequestHandler();
        else if (request instanceof SignInRequest)              return new SignInRequestHandler();
        else if (request instanceof GetDialogContactsRequest)   return new GetDialogContactsRequestHandler();
        else if (request instanceof GetContactsRequest)         return new GetContactsRequestHandler();
        else if (request instanceof CreateDialogRequest)        return new CreateDialogRequestHandler();
        else if (request instanceof GetDialogMessagesRequest)   return new GetDialogMessagesRequestHandler();
        else if (request instanceof SendDialogMessageRequest)   return new SendDialogMessageRequestHandler();
        else if (request instanceof GetConferencesRequest)      return new GetConferencesRequestHandler();
        else if (request instanceof CreateConferenceRequest)    return new CreateConferenceRequestHandler();
        else                                                    throw new UnknownRequestException();
    }
}
