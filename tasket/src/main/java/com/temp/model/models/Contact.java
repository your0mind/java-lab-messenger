package com.temp.model.models;

import java.io.Serializable;

public class Contact implements Serializable {
    private String username;

    public String getUsername() {
        return username;
    }

    public Contact(String username) {
        this.username = username;
    }
}