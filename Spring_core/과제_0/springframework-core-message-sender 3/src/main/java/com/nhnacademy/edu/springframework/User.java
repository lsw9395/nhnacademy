package com.nhnacademy.edu.springframework;

public class User {
    private String email;
    private String phoneNumber;
    private String name;
    public User(){

    }
    public User(String name, String email, String phoneNumber){
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.name = name;
    }
    public String getEmail(){
        return email;
    }

    public String getName() {
        return name;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
}
