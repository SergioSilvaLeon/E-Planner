package com.tecnologiasintech.e_planner.model;

/**
 * Created by sergiosilva on 1/7/18.
 */

public class Student {

    private String name;
    private String email;
    private String school;
    private String tShirtSize;
    private String technology;
    private String oranization;
    private String semester;

    public Student() {
    }

    public Student(String name, String email, String school, String tShirtSize, String technology, String orginization, String semester) {
        this.name = name;
        this.email = email;
        this.school = school;
        this.tShirtSize = tShirtSize;
        this.technology = technology;
        this.oranization = orginization;
        this.semester =semester;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String gettShirtSize() {
        return tShirtSize;
    }

    public void settShirtSize(String tShirtSize) {
        this.tShirtSize = tShirtSize;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getOranization() {
        return oranization;
    }

    public void setOranization(String oranization) {
        this.oranization = oranization;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
