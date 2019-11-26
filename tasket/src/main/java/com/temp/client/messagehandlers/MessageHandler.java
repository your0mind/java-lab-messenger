package com.temp.client.messagehandlers;

import com.temp.common.Message;

public interface MessageHandler<T extends Message> {
    void handle(T message);
}
