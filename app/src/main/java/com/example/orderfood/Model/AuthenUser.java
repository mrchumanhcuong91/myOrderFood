package com.example.orderfood.Model;

public class AuthenUser {
    String username, password, uuid,state;

    public AuthenUser(String username, String password, String uuid, String state) {
        this.username = username;
        this.password = password;
        this.uuid = uuid;
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
