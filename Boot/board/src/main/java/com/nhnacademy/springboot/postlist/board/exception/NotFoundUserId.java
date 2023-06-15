package com.nhnacademy.springboot.postlist.board.exception;

public class NotFoundUserId extends Exception{
    public NotFoundUserId(String id){super("user_ID:"+id);}
}
