package com.nhnacademy.springboot.postlist.board.exception;

public class UserAlreadyExists extends Exception{
    public UserAlreadyExists(String id){super("userID:"+id);}
}
