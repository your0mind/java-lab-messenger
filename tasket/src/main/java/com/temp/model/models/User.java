package com.temp.model.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column
    public String username;

    @Column
    public String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
