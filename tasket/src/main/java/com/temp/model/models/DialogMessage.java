package com.temp.model.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "dialog_messages")
public class DialogMessage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "dialog_id")
    private int dialogId;

    @Column(name = "user_id")
    private int userId;

    @Column
    private String text;

    @GeneratedValue
    @Column(columnDefinition = "DATETIME", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public int getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public DialogMessage() {
    }

    public DialogMessage(int dialogId, int userId, String text) {
        this.dialogId = dialogId;
        this.userId = userId;
        this.text = text;
    }
}