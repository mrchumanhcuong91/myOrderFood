package com.example.orderfood.Model;

public class UserFirebase {
    String email,password, linkAvatar;

    public UserFirebase(String email, String password, String linkAvatar) {
        this.email = email;
        this.password = password;
        this.linkAvatar = linkAvatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLinkAvatar() {
        return linkAvatar;
    }

    public void setLinkAvatar(String linkAvatar) {
        this.linkAvatar = linkAvatar;
    }
}
