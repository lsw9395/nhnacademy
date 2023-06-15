package com.example.javajdbc;

public class Passenger {
    private int passengerNo;
    private String passengerName;
    private int grade;
    private int age;

    public Passenger(int passengerNo,String passengerName, int grade, int age ){
        this.passengerNo = passengerNo;
        this.passengerName =passengerName;
        this.grade = grade;
        this.age = age;
    }
    public void setPassengerNo(int passengerNo){
        this.passengerNo = passengerNo;
    }
    public int getPassengerNo(){
        return passengerNo;
    }
    public void setPassengerName(String passengerName){
        this.passengerName =passengerName;
    }
    public String getPassengerName(){
        return passengerName;
    }
    public void setGrade(int grade){
        this.grade = grade;
    }
    public int getGrade(){
        return grade;
    }
    public void setAge(int age){
        this.age = age;
    }
    public int getAge(){
        return age;
    }
    @Override
    public String toString(){
        return "{Passenger:(No:"+passengerNo+" ,Name:"+passengerName+" ,Grade: "+grade+" ,Age: "+age+")}";
    }
}
