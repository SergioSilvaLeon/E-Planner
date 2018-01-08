package com.tecnologiasintech.e_planner.model;

/**
 * Created by sergiosilva on 1/8/18.
 */

public class Event {

    private String name;
    private String date;
    private String description;
    private boolean isPopular;
    private String host;
    private String key;

    // lista de alumnos y mentores


    public Event() {
    }

    public Event(String name, String date, String description, boolean isPopular, String host, String key) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.isPopular = isPopular;
        this.host = host;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPopular() {
        return isPopular;
    }

    public void setPopular(boolean popular) {
        isPopular = popular;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
