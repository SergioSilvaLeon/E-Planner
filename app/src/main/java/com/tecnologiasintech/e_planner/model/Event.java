package com.tecnologiasintech.e_planner.model;

import java.util.ArrayList;
import java.util.List;

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
    private boolean isSpecific;


    public Event() {
    }

    public Event(String name, String date, String description, boolean isPopular, String host, String key, boolean isSpecific) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.isPopular = isPopular;
        this.host = host;
        this.key = key;
        this.isSpecific = isSpecific;
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

    public boolean isSpecific() {
        return isSpecific;
    }

    public void setSpecific(boolean specific) {
        isSpecific = specific;
    }
}
