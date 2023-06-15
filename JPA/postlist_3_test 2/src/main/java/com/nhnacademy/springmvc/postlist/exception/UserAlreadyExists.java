package com.nhnacademy.springmvc.postlist.exception;

public class UserAlreadyExists extends Exception{
    public UserAlreadyExists(String id){super("userID:"+id);}
}
