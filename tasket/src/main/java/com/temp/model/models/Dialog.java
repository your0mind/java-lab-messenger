package com.temp.model.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dialogs")
public class Dialog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user1_id")
    private int user1Id;

    @Column(name = "user2_id")
    private int user2Id;

    public int getId() {
        return id;
    }

    public int getUser1Id() {
        return user1Id;
    }

    public int getUser2Id() {
        return user2Id;
    }

    public Dialog() {
    }

    public Dialog(int user1Id, int user2Id) {
        this.user1Id = user1Id;
        this.user2Id = user2Id;
    }
}