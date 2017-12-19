package com.tecnologiasintech.e_planner.model;

/**
 * Created by sergiosilva on 11/23/17.
 */

public class User {

    private String email;
    private String fullName;
    private boolean isAdmin;

    public User(){}

    public User(String email, String fullName, boolean isAdmin) {
        this.email = email;
        this.fullName = fullName;
        this.isAdmin = isAdmin;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
