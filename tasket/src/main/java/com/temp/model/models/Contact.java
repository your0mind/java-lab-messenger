package com.temp.model.models;

import java.io.Serializable;

public class Contact implements Serializable {
    private String username;

    public String getUsername() {
        return username;
    }

    public Contact(User user) {
        username = user.getUsername();
    }

    @Override
    public String toString() {
        return username;
    }
}