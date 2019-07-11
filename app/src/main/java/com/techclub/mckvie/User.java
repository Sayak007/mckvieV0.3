package com.techclub.mckvie;

public class User {
    public String name, email, id, admin, dept, roll, phn, year;

    public User(){

    }

    public User(String name, String email, String id, String admin, String dept, String roll, String phn, String year) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.admin = admin;
        this.dept = dept;
        this.roll = roll;
        this.phn = phn;
        this.year = year;
    }

    public String getName() {return name;}
}