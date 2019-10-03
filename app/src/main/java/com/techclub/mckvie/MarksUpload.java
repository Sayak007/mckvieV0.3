package com.techclub.mckvie;

import android.util.Log;

import com.google.firebase.database.FirebaseDatabase;

public class MarksUpload {
    String dept,year,sem,ct,roll,paper,marks;
    int n;
    public MarksUpload(){

    }
    public MarksUpload(String dept,String year,String sem,String ct,String roll,String paper,String marks){
        this.dept=dept;
        this.year=year;
        this.sem=sem;
        this.ct= ct;
        this.roll=roll;
        this.paper=paper;
        this.marks=marks;
    }

    public String getDept(){
        return dept;
    }
    public void setDept(String dept){
        this.dept=dept;
    }
    public String getyear(){
        return year;
    }
    public void setyear(String year){
        this.year=year;
    }
    public String getSem(){
        return sem;
    }
    public  void setSem(String sem){
        this.sem=sem;
    }
    public String getct(){
        return ct;
    }
    public void setCt(String ct){
        this.ct=ct;
    }
    public String getroll(){
        return roll;
    }
    public void setRoll(String roll){
        this.roll=roll;
    }
    public String getPaper(){
        return paper;
    }
    public void setPaper(String paper){
        this.paper=paper;
    }
    public String getMarks(){
        return marks;
    }
    public void setMarks(String marks){
        this.marks=marks;
    }
}
