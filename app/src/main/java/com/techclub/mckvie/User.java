package com.techclub.mckvie;

public class User {
    public String name, email, id, admin, dept, roll, phn, batch;

    public User(){

    }

    public User(String name, String email, String id, String admin, String dept, String roll, String phn, String batch) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.admin = admin;
        this.dept = dept;
        this.roll = roll;
        this.phn = phn;
        this.batch = batch;
    }

    public String getName() {return name;}
}