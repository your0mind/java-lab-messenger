package com.temp.client.messagehandlers;

import com.temp.client.exceptions.UnknownMessageException;
import com.temp.common.Message;
import com.temp.common.responses.*;
import com.temp.common.updates.DialogContactUpdate;

public class MessageHandlerBuilder {
    public static MessageHandler build(Message message) throws UnknownMessageException {
        if      (message instanceof SignUpResponse)             return new SignUpResponseHandler();
        else if (message instanceof SignInResponse)             return new SignInResponseHandler();
        else if (message instanceof GetDialogContactsResponse)  return new GetDialogContactsResponseHandler();
        else if (message instanceof GetContactsResponse)        return new GetContactsResponseHandler();
        else if (message instanceof DialogContactUpdate)        return new DialogContactUpdateHandler();
        else if (message instanceof ErrorResponse)              return new ErrorResponseHandler();
        else                                                    throw new UnknownMessageException();
    }
}
