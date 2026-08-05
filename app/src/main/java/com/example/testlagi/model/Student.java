package com.example.testlagi.model;

public class Student {
    private int id;
    private String nis;
    private String name;
    private String major;

    public Student () {
    }

    public Student(int id, String nis, String  name, String  major) {
        this.id = id;
        this.nis = nis;
        this.name = name;
        this.major = major;
    }

    public Student(String nis, String  name, String  major) {
        this.nis = nis;
        this.name = name;
        this.major = major;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNis() { return nis; }
    public void setNis(String nis) { this.nis = nis; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
}

