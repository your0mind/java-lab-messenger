package com.temp.common.responses;

import com.temp.common.models.ChatMessage;

import java.util.List;

public class GetDialogMessagesResponse extends Response {
    private List<ChatMessage> messages;

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public GetDialogMessagesResponse(String errorMessage) {
        super(errorMessage);
    }

    public GetDialogMessagesResponse(List<ChatMessage> messages) {
        this.messages = messages;
    }
}
