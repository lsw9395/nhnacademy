package com.nhnacademy.springmvc.postlist.exception;

public class NotFoundUser extends Exception{
    public NotFoundUser(String page){super("user_page:"+page);}
}
