package com.temp.client.messagehandlers;

import com.temp.client.Client;
import com.temp.common.Message;

public interface MessageHandler<T extends Message> {
    void handle(T message, Client client);
}
