package com.example.spandanaravulapalli.firebasedemo;

import java.io.Serializable;

/**
 * Created by spandanaravulapalli on 6/23/16.
 */
public class User implements Serializable{

    String username,password,email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
