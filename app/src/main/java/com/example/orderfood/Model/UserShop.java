package com.example.orderfood.Model;

public class UserShop {
    String name, password, id_people;

    public UserShop(String name, String password, String id_people) {
        this.name = name;
        this.password = password;
        this.id_people = id_people;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId_people() {
        return id_people;
    }

    public void setId_people(String id_people) {
        this.id_people = id_people;
    }
}
