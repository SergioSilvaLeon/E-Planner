package com.tecnologiasintech.e_planner.model;

/**
 * Created by sergiosilva on 11/23/17.
 */

public class User {

    private String email;
    private String fullName;
    private boolean isAdmin;
    private String stack;
    private String key;

    public User(){}

    public User(String email, String fullName, boolean isAdmin, String stack, String key) {
        this.email = email;
        this.fullName = fullName;
        this.isAdmin = isAdmin;
        this.stack = stack;
        this.key = key;
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

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
