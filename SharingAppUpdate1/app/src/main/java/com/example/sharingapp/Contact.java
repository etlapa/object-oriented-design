package com.example.sharingapp;

import java.util.UUID;

public class Contact {
    private String id;
    private String username;
    private String email;

    public Contact(String username_str, String email_str, String id) {
        username = username_str;
        email = email_str;
        if (id == null) {
            setId();
        } else {
            updateId(id);
        }
    }

    public String getId() {
        return id;
    }

    public void setId() {
        id = UUID.randomUUID().toString();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void updateId(String id) {
        this.id = id;
    }
}
