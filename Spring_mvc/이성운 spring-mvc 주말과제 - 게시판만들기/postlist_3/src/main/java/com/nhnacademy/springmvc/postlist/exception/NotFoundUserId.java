package com.nhnacademy.springmvc.postlist.exception;

public class NotFoundUserId extends Exception{
    public NotFoundUserId(String id){super("user_ID:"+id);}
}
