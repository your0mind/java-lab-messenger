package com.temp.client.messagehandlers;

import com.temp.client.exceptions.UnknownMessageException;
import com.temp.common.Message;
import com.temp.common.responses.ErrorResponse;
import com.temp.common.responses.GetDialogContactsResponse;
import com.temp.common.responses.LoginResponse;
import com.temp.common.responses.RegisterResponse;

public class MessageHandlerBuilder {
    public static MessageHandler build(Message message) throws UnknownMessageException {
        if      (message instanceof RegisterResponse)   return new RegisterResponseHandler();
        else if (message instanceof LoginResponse)      return new LoginResponseHandler();
        else if (message instanceof GetDialogContactsResponse) return new GetDialogContactsResponseHandler();
        else if (message instanceof ErrorResponse)      return new ErrorResponseHandler();
        else                                            throw new UnknownMessageException();
    }
}
