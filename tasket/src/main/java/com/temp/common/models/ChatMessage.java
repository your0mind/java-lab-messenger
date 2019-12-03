package com.temp.common.models;

import java.io.Serializable;
import java.util.Date;

public class ChatMessage implements Serializable {
    private Contact sender;
    private String text;
    private Date date;

    public Contact getSender() {
        return sender;
    }

    public void setSender(Contact sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ChatMessage(Contact sender, String text, Date date) {
        this.sender = sender;
        this.text = text;
        this.date = date;
    }
}
