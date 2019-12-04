package com.temp.common.responses;

import com.temp.common.models.ChatMessage;

public class SendDialogMessageResponse extends Response {
    private ChatMessage sentMessage;

    public ChatMessage getSentMessage() {
        return sentMessage;
    }

    public SendDialogMessageResponse(ErrorMessage error) {
        super(error);
    }

    public SendDialogMessageResponse(ChatMessage sentMessage) {
        this.sentMessage = sentMessage;
    }
}
