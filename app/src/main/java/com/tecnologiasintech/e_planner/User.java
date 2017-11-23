package com.tecnologiasintech.e_planner;

/**
 * Created by sergiosilva on 11/23/17.
 */

public class User {

    private String email;
    private String fullName;

    public User(){}

    public User(String email, String fullName) {
        this.email = email;
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
